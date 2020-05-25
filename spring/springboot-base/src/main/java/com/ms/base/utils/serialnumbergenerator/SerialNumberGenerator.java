package com.ms.base.utils.serialnumbergenerator;

/**
 * SerialNumberGenerator
 */
public class SerialNumberGenerator {

	// the length of serial number
	private static int stampTimeLength = 10;
	private static int idLength = 4;
	private static int randomLength = 4;
	
	public static Long generatorSerial() {
		return generatorSerial(stampTimeLength, randomLength);
	}

	/**
	 * return serial number. serial number = (stampTimeLength 位时间戳) +
	 * (idLength 位用户ID) + (randomLength 位随机数)
	 */
	public static Long generatorSerial(Long id) {
		// 时间戳截取
		String stampTime = SerialNumberGeneratorUtil.truncateStampTime(stampTimeLength);
		// ID号截取
		String idStr = truncateID(String.valueOf(id));
		// 随机数生成
		String random = SerialNumberGeneratorUtil.generateRandom(randomLength);

		Long result = Long.valueOf(stampTime + idStr + random);
		return result;
	}

	/**
	 * return serial number. serial number = (stampTimeLength 位时间戳) +
	 * (idLength 位用户ID) + (randomLength 自定义数字)
	 */
	public static Long generatorSerial(Long id, int customNum) {
		// 时间戳截取
		String stampTime = SerialNumberGeneratorUtil.truncateStampTime(stampTimeLength);
		// ID号截取
		String idStr = truncateID(String.valueOf(id));
		// 自定义数字
		String customNumStr = truncateID(String.valueOf(customNum));

		Long result = Long.valueOf(stampTime + idStr + customNumStr);
		return result;
	}

	/**
	 * return serial number. serial number = (stampTimeLength 位时间戳) + (randomLength 位随机数)
	 */
	public static Long generatorSerial(int stampTimeLength, int randomLength) {
		// 时间戳截取
		String stampTime = SerialNumberGeneratorUtil.truncateStampTime(stampTimeLength);
		// 随机数生成
		String random = SerialNumberGeneratorUtil.generateRandom(randomLength);

		Long result = Long.valueOf(stampTime + random);
		return result;
	}

	private static String truncateID(String id) {
		String idSerial = "";

		int idLen = id.length();
		// if the length of the id is smaller than idLength, fill the id with zero.
		if (idLen < idLength) {
			idSerial = SerialNumberGeneratorUtil.fillWithZero(id,idLength);
		}
		// if the length of the id is larger than idLength or equal idLength,
		// get the result with length idLength from the end of the id.
		else {
			idSerial = id.substring(idLen - idLength);
		}
		return idSerial;
	}
	
	public static Long generatorSerialGoods(int customNum) {
		// 时间戳截取
		String stampTime = SerialNumberGeneratorUtil.truncateStampTime(stampTimeLength);
//		// ID号截取
//		String idStr = truncateID(String.valueOf(id));
		
		String idSerial = "";
		String customNumStr = String.valueOf(customNum);
		int idLen = customNumStr.length();
		if (idLen < idLength) {
			idSerial = SerialNumberGeneratorUtil.fillWithZero(customNumStr, 8);
		}else {
			idSerial = customNumStr.substring(idLen - idLength);
		}

		Long result = Long.valueOf(stampTime + idSerial);
		return result;
	}
}
