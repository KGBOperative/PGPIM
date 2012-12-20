//package org.security.apps.pim;
//
//import org.spongycastle.jce.provider.BouncyCastleProvider;
//import org.spongycastle.openssl.PEMReader;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.security.NoSuchProviderException;
//import java.security.SecureRandom;
//import java.security.Security;
//import java.util.Date;
//
//import org.spongycastle.bcpg.ArmoredOutputStream;
//import org.spongycastle.bcpg.CompressionAlgorithmTags;
//import org.spongycastle.jce.provider.BouncyCastleProvider;
//import org.spongycastle.openpgp.*;//.openpgp.PGPCompressedData;
//import org.spongycastle.openpgp.PGPCompressedDataGenerator;
//import org.spongycastle.openpgp.PGPEncryptedDataGenerator;
//import org.spongycastle.openpgp.PGPEncryptedDataList;
//import org.spongycastle.openpgp.PGPException;
//import org.spongycastle.openpgp.PGPLiteralData;
//import org.spongycastle.openpgp.PGPLiteralDataGenerator;
//import org.spongycastle.openpgp.PGPObjectFactory;
//import org.spongycastle.openpgp.PGPPBEEncryptedData;
//import org.spongycastle.openpgp.PGPUtil;
//import org.spongycastle.openpgp.operator.jcajce.JcaPGPDigestCalculatorProviderBuilder;
//import org.spongycastle.openpgp.operator.jcajce.JcePBEDataDecryptorFactoryBuilder;
//import org.spongycastle.openpgp.operator.jcajce.JcePBEKeyEncryptionMethodGenerator;
//import org.spongycastle.openpgp.operator.jcajce.JcePGPDataEncryptorBuilder;
//import org.spongycastle.util.io.Streams;
//
//public class Encryptor {
//
//	public Encryptor() {
//		// Fill in later
//	}
//
//	public byte[] encrypt(byte[] msg, byte[] publicKey) {
//		return new byte[1];
//	}
//
//	public static byte[] decrypt(byte[] encrypted, char[] passPhrase)
//			throws IOException, PGPException, NoSuchProviderException {
//		InputStream in = new ByteArrayInputStream(encrypted);
//
//		in = PGPUtil.getDecoderStream(in);
//
//		PGPObjectFactory pgpF = new PGPObjectFactory(in);
//		PGPEncryptedDataList enc;
//		Object o = pgpF.nextObject();
//
//		//
//		// the first object might be a PGP marker packet.
//		//
//		if (o instanceof PGPEncryptedDataList) {
//			enc = (PGPEncryptedDataList) o;
//		} else {
//			enc = (PGPEncryptedDataList) pgpF.nextObject();
//		}
//
//		PGPPBEEncryptedData pbe = (PGPPBEEncryptedData) enc.get(0);
//
//		InputStream clear = pbe
//				.getDataStream(new JcePBEDataDecryptorFactoryBuilder(
//						new JcaPGPDigestCalculatorProviderBuilder()
//								.setProvider("SC").build()).setProvider("SC")
//						.build(passPhrase));
//
//		PGPObjectFactory pgpFact = new PGPObjectFactory(clear);
//
//		PGPCompressedData cData = (PGPCompressedData) pgpFact.nextObject();
//
//		pgpFact = new PGPObjectFactory(cData.getDataStream());
//
//		PGPLiteralData ld = (PGPLiteralData) pgpFact.nextObject();
//
//		return Streams.readAll(ld.getInputStream());
//	}
//}