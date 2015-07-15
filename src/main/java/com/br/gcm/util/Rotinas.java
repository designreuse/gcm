package com.br.gcm.util;

import com.br.gcm.dao.UsuarioDao;
import com.br.gcm.model.Usuario;
import edu.vt.middleware.dictionary.ArrayWordList;
import edu.vt.middleware.dictionary.WordList;
import edu.vt.middleware.dictionary.WordListDictionary;
import edu.vt.middleware.dictionary.sort.ArraySorter;
import edu.vt.middleware.dictionary.sort.QuickSort;
import edu.vt.middleware.password.*;
import org.apache.commons.lang3.ArrayUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.String;

@Component
public class Rotinas {

    // Charsets
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final Charset ISO88591 = Charset.forName("ISO-8859-1");

    private static final Logger logger = LoggerFactory.getLogger(Rotinas.class);
    public static final Locale PT_BR = new Locale("pt", "BR");

    @Inject private UsuarioDao usuarioDao;

    public Locale getPT_BR() {
        return PT_BR;
    }

    public String md5(byte[] byteArr) {
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("MD5");
            md5Digest.reset();
            md5Digest.update(byteArr);
            byte[] hash = md5Digest.digest();
            String hashText = new BigInteger(1, hash).toString(16);
            while (hashText.length() < 32)
                hashText = "0" + hashText;
            return hashText;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String md5(final String input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] messageDigest = md.digest(input.getBytes());
        final BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }

    public String decrypt(final String encryptedData, final String initialVector, final String secretKey) {
        String decryptedData = null;
        try {
            final Cipher cipher = initCipher(Cipher.DECRYPT_MODE, initialVector, secretKey);
            final byte[] encryptedByteArray = (new BASE64Decoder()).decodeBuffer(encryptedData);
            final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
            decryptedData = new String(decryptedByteArray, "UTF8");
        } catch (Exception e) {
            System.err.println("Problem decrypting the data");
            e.printStackTrace();
        }
        return decryptedData;
    }

    private Cipher initCipher(final int mode, final String initialVectorString, final String secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        final SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
        final IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
        final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(mode, skeySpec, initialVector);
        return cipher;
    }



    public int toDelphiIntDate(Date data) {
        Days days = Days.daysBetween(
                new LocalDate(1899, 12, 30),    // data inicial do delphi
                LocalDate.fromDateFields(data));
        return days.getDays();
    }

    public String md5(String str, Charset charset) {
        return md5(str.getBytes(charset));
    }

    public String geraGuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Gera Hash SHA256 de uma String.
     * @param str String a ter o hash gerado
     * @return Hash SHA256 da String
     */
    public String sha256(String str) {
        try {
            MessageDigest md5Digest = MessageDigest.getInstance("SHA-256");
            md5Digest.reset();
            md5Digest.update(str.getBytes());
            byte[] hash = md5Digest.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hash.length; i++)
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16)
                        .substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unused")
    public String encodeAES(String secretKey, String dataToEncrypt) {
        byte[] secretKeyArr = secretKey.getBytes();
        byte[] dataToEncryptArr = dataToEncrypt.getBytes();
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec k = new SecretKeySpec(secretKeyArr, "AES");
            c.init(Cipher.ENCRYPT_MODE, k);
            byte[] encryptedData = c.doFinal(dataToEncryptArr);
            return new String(encryptedData);
        } catch (Exception e) {
            logger.error("Erro ao criptografar para AES", e);
            return null;
        }
    }

    @SuppressWarnings("unused")
    public String decodeAES(String secretKey, String dataToDecrypt) {
        byte[] secretKeyArr = secretKey.getBytes();
        byte[] dataToDecryptArr = dataToDecrypt.getBytes();
        try {
            Cipher c = Cipher.getInstance("AES");
            SecretKeySpec k = new SecretKeySpec(secretKeyArr, "AES");
            c.init(Cipher.DECRYPT_MODE, k);
            byte[] decryptedData = c.doFinal(dataToDecryptArr);
            return new String(decryptedData);
        } catch (Exception e) {
            logger.error("Erro ao descriptografar para AES", e);
            return null;
        }
    }

    @SuppressWarnings("unused")
    public byte[] encrypt(byte[] ivBytes, byte[] keyBytes, byte[] mes) {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
            return  cipher.doFinal(mes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unused")
    public String getRemoteAddress() {
        String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
        return remoteAddress;
    }

    @SuppressWarnings("unused")
    public String encodeBlowfish(String secretKey, String dataToEncrypt) {
        byte[] secretKeyArr = secretKey.getBytes();
        byte[] dataToEncryptArr = dataToEncrypt.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(secretKeyArr, "Blowfish");
        try {
            Cipher c = Cipher.getInstance("Blowfish");
            c.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedData = c.doFinal(dataToEncryptArr);
            return new String(encryptedData);
        } catch (Exception e) {
            logger.error("Erro ao criptografar para Blowfish", e);
            return null;
        }
    }

    @SuppressWarnings("unused")
    public String decodeBlowfish(String secretKey, String dataToDecrypt) {
        byte[] secretKeyArr = secretKey.getBytes();
        byte[] dataToDecryptArr = dataToDecrypt.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(secretKeyArr, "Blowfish");
        try {
            Cipher c = Cipher.getInstance("Blowfish");
            c.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] decryptedData = c.doFinal(dataToDecryptArr);
            return new String(decryptedData);
        } catch (Exception e) {
            logger.error("Erro ao descriptografar para Blowfish", e);
            return null;
        }
    }

    public String extraiNumeros(String str) {
        return str.replaceAll("\\D", "");
    }

    /**
     * Encriptografa igual ao Delphi
     * @param valueToEncrypt
     * @param key1
     * @param key2
     * @param key3
     * @return Valor criptografado em hexadecimal
     *
     * http://stackoverflow.com/questions/15885898/delphi-encrypt-convert-java-code/15886299#15886299
     * http://stackoverflow.com/questions/15933038/delphi-decrypt-convert-java-code?answertab=active#tab-top
     */
    public String delphiEncrypt(String valueToEncrypt, int key1, int key2, int key3) {
        String result = "";
        for(int i=0; i< valueToEncrypt.length(); i++){
            byte bValue = valueToEncrypt.substring(i).getBytes()[0];
            int rValue = bValue^(key1 >> 8);
            key1 = ((rValue + key1) * key2 + key3) & 0xffff;
            result += valueToHex(rValue);
        }
        return result.toUpperCase();
    }

    /**
     * Descriptografa igual ao Delphi
     * @param valueToDecrypt
     * @param key1
     * @param key2
     * @param key3
     * @return Valor descriptografado
     *
     * http://stackoverflow.com/questions/15885898/delphi-encrypt-convert-java-code/15886299#15886299
     * http://stackoverflow.com/questions/15933038/delphi-decrypt-convert-java-code?answertab=active#tab-top
     */
    public String delphiDecrypt(String valueToDecrypt, int key1, int key2, int key3) {
        int[] input = hexStringToIntArray(valueToDecrypt);
        int[] output = new int[input.length];
        for (int i=0; i<output.length; i++) {
            output[i] = input[i]^(key1>>8) & 0xff;
            key1 = ((input[i]+key1)*key2+key3) & 0xffff;
        }
        return intArrayToHexString(output);
    }

    private int[] hexStringToIntArray(String s) {
        int len = s.length();
        int[] data = new int[len / 2];
        for (int i=0; i<len; i+=2) {
            data[i / 2] =  ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private String intArrayToHexString(int[] a) {
        StringBuilder sb = new StringBuilder(a.length);
        for (int i=0; i<a.length; i++) {
            sb.append((char) a[i]);
        }
        return sb.toString();
    }

    private String valueToHex(int myInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(myInt & 0xff));
        if (sb.length() < 2) {
            sb.insert(0, '0'); // pad with leading zero if needed
        }
        return sb.toString();
    }

    public String toHex(byte[] data, int length)
    {
        StringBuffer    buf = new StringBuffer();
        for (int i = 0; i != length; i++) {
            int v = data[i] & 0xff;
            String digits = "0123456789abcdef";
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        return buf.toString();
    }

    public String toHex(byte[] data) {
        return toHex(data, data.length);
    }

    public FieldError createError(String objectName, String field, String rejectedValue, String defaultMessage) {
        return new FieldError(objectName, field, rejectedValue, false, null, null, defaultMessage);
    }

    public FieldError createError(String objectName, String field, Integer rejectedValue, String defaultMessage) {
        return new FieldError(objectName, field, rejectedValue, false, null, null, defaultMessage);
    }

    public FieldError createError(String objectName, String field, Long rejectedValue, String defaultMessage) {
        return new FieldError(objectName, field, rejectedValue, false, null, null, defaultMessage);
    }

    public FieldError createError(String objectName, String field, Date rejectedValue, String defaultMessage) {
        return new FieldError(objectName, field, rejectedValue, false, null, null, defaultMessage);
    }

    public List<String> checaPoliticaDeSenha(String senha) {

        // Implementar histórico de senha
        // http://code.google.com/p/vt-middleware/wiki/vtpassword

        // password must be between 8 and 16 chars long
        LengthRule lengthRule = new LengthRule(8, 30);

        // don't allow whitespace
        WhitespaceRule whitespaceRule = new WhitespaceRule();

        // control allowed characters
        CharacterCharacteristicsRule charRule = new CharacterCharacteristicsRule();
        // require at least 1 digit in passwords
        charRule.getRules().add(new DigitCharacterRule(1));
        // require at least 1 non-alphanumeric char
        //charRule.getRules().add(new NonAlphanumericCharacterRule(1));
        // require at least 1 upper case char
        charRule.getRules().add(new UppercaseCharacterRule(1));
        // require at least 1 lower case char
        charRule.getRules().add(new LowercaseCharacterRule(1));
        // require at least 3 of the previous rules be met
        charRule.setNumberOfCharacteristics(3);

        // don't allow alphabetical sequences
        AlphabeticalSequenceRule alphaSeqRule = new AlphabeticalSequenceRule();

        // don't allow numerical sequences of length 3
        NumericalSequenceRule numSeqRule = new NumericalSequenceRule(3, false);

        // don't allow qwerty sequences
        QwertySequenceRule qwertySeqRule = new QwertySequenceRule();

        // don't allow 3 repeat characters
        RepeatCharacterRegexRule repeatRule = new RepeatCharacterRegexRule(3);

        // create a dictionary for searching
        WordList awl = new ArrayWordList(new String[] {
                "senha", "jessica", "jéssica",
                "password", "hany", "silvana",
                "marcelo", "bob", "roberto",
                "andre", "rodrigo", "emerson",
                "andré", "aniversário", "aniversario",
                "vls021130", "vls02", "vls021130",
                "021130", "Vls021130", "flavio",
                "flávio", "meire", "joao",
                "joão", "augusta", "queca",
                "keka", "keca", "kêka",
                "quêca", "queka", "quêka",
                "day", "dayanna", "dayana",
                "marcio", "márcio", "rod"
        }, false, new QuickSort());
        WordListDictionary dict = new WordListDictionary(awl);
        DictionarySubstringRule dictRule = new DictionarySubstringRule(dict);
        dictRule.setWordLength(3); // size of words to check in the password
        dictRule.setMatchBackwards(true); // match dictionary words backwards

        // group all rules together in a List
        List<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(lengthRule);
        ruleList.add(whitespaceRule);
        ruleList.add(charRule);
        ruleList.add(alphaSeqRule);
        ruleList.add(numSeqRule);
        ruleList.add(qwertySeqRule);
        ruleList.add(repeatRule);
        ruleList.add(dictRule);

        MessageResolver resolver = null;
        try {
            Resource res = new ClassPathResource("vt-password.properties");
            Properties props = new Properties();
            props.load(new FileInputStream(res.getFile()));
            resolver = new MessageResolver(props);
        } catch (IOException e) {
            logger.error("Erro ao ler arquivo de tradução do vt-password", e);
        }

        PasswordValidator validator = new PasswordValidator(resolver, ruleList);
        PasswordData passwordData = new PasswordData(new Password(senha));

        RuleResult result = validator.validate(passwordData);
        if (result.isValid()) {
            return null;
        } else {
            return new ArrayList<>(validator.getMessages(result));
        }
    }

    public String geraSenhaComPoliticasAtuais() {
        // create a password generator
        PasswordGenerator generator = new PasswordGenerator();

        // create character rules to generate passwords with
        List<CharacterRule> rules = new ArrayList<CharacterRule>();
        rules.add(new DigitCharacterRule(1));
        //rules.add(new NonAlphanumericCharacterRule(1));
        rules.add(new UppercaseCharacterRule(1));
        rules.add(new LowercaseCharacterRule(1));

        // gera
        return generator.generatePassword(8, rules);
    }

    public Usuario usuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return usuarioLogado(auth);
    }

    public Usuario usuarioLogado(Authentication auth) {
        if (auth == null)
            return null;
        if (auth.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) auth.getPrincipal();
            return usuarioDao.byNome(details.getUsername());
        } else {
            return null;
        }
    }

    public String tipoUsuarioLogado() {
        Usuario usuarioLogado = usuarioLogado();
          return "ADMIN";
       /* return usuarioLogado != null
                ? StringUtils.replace(usuarioLogado.getAcesso(), "ROLE_", "")
                : "SIS";  */
    }

    public String tipoUsuarioLogado(Usuario usuarioLogado) {
          return "ADMIN";
       /* return usuarioLogado != null
                ? StringUtils.replace(usuarioLogado.getAcesso(), "ROLE_", "")
                : "SIS";  */
    }

    public UserDetails getCurrentUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth == null)
            return null;
        return (UserDetails) auth.getPrincipal();
    }

    public final <T> T getNullableValue(T returnType, String colName, ResultSet rs) {
        Object colValue = null;
        try {
            colValue = rs.getObject(colName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (T) colValue;
    }

    public String gerarSenhaAleatoria(){
        String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String senha="";

        for (int x=0; x<10; x++){
            int j = (int) (Math.random()*carct.length);
            senha += carct[j];
        }

        return senha;
    }
}
