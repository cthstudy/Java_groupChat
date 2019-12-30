package cn.util;
/**
 * @author 曹天化
 */

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import net.iharder.Base64;

public class CTHUtil {

		/**
		 * 日期处理
		 */
		private static final String FT = "yyyy-MM-dd HH:mm:ss";
		private static final String FT1 = "yyyy年MM月dd日 HH时mm分ss秒";
		// 当前系统时间
		private static final Date DT = new Date(System.currentTimeMillis());
		private static Logger log = Logger.getLogger(CTHUtil.class);
		//用户生成二维码
		private static String[] arr = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	    private static String format = "png";
	    //表单验证
	    private static char[] ch = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', //
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', //
				'r', 's', 't', 'u', 'v', 'w', 'y', 'z', //
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', //
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', //
				'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'Z', 'X', 'x', //
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '_' };

		static {
			for (int i = 0; i < 529;) {
				// User u = new User();
				// u.setUserName(generateRandomNameOrPassWord());
				// u.setPassWord(generateRandomNameOrPassWord());
				// Map<Integer, Date> map =
				// changeIdentityToBirthAndAge(generateRandomIndentityNo());
				// Set<Integer> set = map.keySet();
				// Iterator<Integer> it = set.iterator();
				// Integer key = it.next();
				// u.setAge(key);
				// u.setBirthday(map.get(key));
				// list.add(u);
				i++;
			}
		}

		/**
		 * 把字符串转化成java.util.Date
		 * @param source
		 *	字符串
		 * @return
		 * java.util.Date
		 */
		public static java.util.Date convertStringToUtilDate(String source) {
			SimpleDateFormat ft = new SimpleDateFormat(FT);
			try {
				java.util.Date date = ft.parse(source);
				return date;
			} catch (Exception e) {
				log.debug(e);
			}
			return null;
		}

		/**
		 * 把字java.util.Date化成符串转
		 * @param source
		 *	字符串
		 * @return
		 * String
		 */
		public static String convertUtilDateToString(java.util.Date source) {
			SimpleDateFormat ft = new SimpleDateFormat(FT);
			try {
				String str = ft.format(source);
				return str;
			} catch (Exception e) {
				log.debug(e);
			}
			return null;
		}

		/**
		 * 把字符串转化成java.sql.Date
		 * @param source
		 * @return
		 * java.sql.Date
		 */
		public static java.sql.Date convertStringToSQLDate(String source) {
			SimpleDateFormat ft = new SimpleDateFormat(FT);
			try {
				java.util.Date date = ft.parse(source);
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
				return sqldate;
			} catch (ParseException e) {
				log.debug(e);
			}
			return null;
		}

		/**
		 * 把util转成sql
		 * @param date
		 * @return
		 * java.sql.Date
		 */
		public static java.sql.Date convertUtilDateToSQLDate(java.util.Date date) {
			try {
				java.sql.Date sqldate = new java.sql.Date(date.getTime());
				return sqldate;
			} catch (Exception e) {
				log.debug(e);
			}
			return null;
		}

		/**
		 * 把util转成TimeStamp
		 * @param 
		 * java.util.Date
		 * @return
		 * Timestamp
		 */
		public static Timestamp convertUtilDateToTimeStamp(java.util.Date date) {
			try {
				Timestamp sqldate = new Timestamp(date.getTime());
				return sqldate;
			} catch (Exception e) {
				log.debug(e);
			}
			return null;
		}

		/**
		 * 把Timestamp数据类型转化成util.Date
		 * @param date
		 * @return
		 * java.util.Date
		 */
		public static java.util.Date convertTimeStampToUtilDate(Timestamp date) {
			try {
				java.util.Date sqldate = new java.util.Date(date.getTime());
				return sqldate;
			} catch (Exception e) {
				log.debug(e);
			}
			return null;
		}

		/**
		 * 根据util.Date生成年龄
		 * @param date
		 * @return
		 * 年龄
		 */
		public static Integer convertUtilDateToAge(java.util.Date date) {
			try {
				Calendar now = Calendar.getInstance();
				Integer year = now.get(Calendar.YEAR);
				Calendar target = Calendar.getInstance();
				target.setTime(date);
				Integer targetYear = target.get(Calendar.YEAR);
				return (year - targetYear == 0) ? (year - targetYear + 1) : (year - targetYear);
			} catch (Exception e) {
				log.debug(e);
			}
			return 1;
		}

		/**
		 * 把字符串转化成java.util.Date
		 * @param 字符串
		 * @return
		 * Timestamp
		 */
		public static Timestamp convertStringToTimestamp(String source) {
			if (source != null) {
				try {
					return new Timestamp(convertStringToUtilDate(source).getTime());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
		/**
		 * @return
		 * 获取当前日期前30天的日期 集合 
		 */
		public static List<String> getDateFor30List(){
			
			SimpleDateFormat fmt  = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String date=fmt.format(today);
			String maxDateStr = date;
			String minDateStr = "";
			Calendar calc =Calendar.getInstance();
			List<String> datefor30List=new ArrayList<String>();
			try {
				for(int i=0;i<30;i++){
		        	calc.setTime(fmt.parse(maxDateStr));  
		            calc.add(calc.DATE, -i);   
		            Date minDate = calc.getTime();  
		            minDateStr = fmt.format(minDate);  
		            datefor30List.add(minDateStr);
		            String[] arr=new String[30];
		            arr[i]=minDateStr;
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return datefor30List;
		}
		
		/**
		 * @return
		 * 获取当前日期前30天的日期数组
		 */
		public static String[] getDateFor30arr(){
			
			SimpleDateFormat fmt  = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String date=fmt.format(today);
			String maxDateStr = date;
			String minDateStr = "";
			Calendar calc =Calendar.getInstance();
			String[] arr=new String[30];
			try {
				for(int i=0;i<30;i++){
		        	calc.setTime(fmt.parse(maxDateStr));  
		            calc.add(calc.DATE, -i);   
		            Date minDate = calc.getTime();  
		            minDateStr = fmt.format(minDate);  
		            arr[i]=minDateStr;
				}
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return arr; 
		}
	
	/**
	 * @return
	 * 生成随机的用户名或密码
	*/
	public static String createRandomNameOrPassWord() {
		StringBuffer sb = new StringBuffer();
		sb.append(ch[(int) (Math.random() * 52)]);// 首字母
		int num = (int) (Math.random() * (11 - 5 + 1)) + 5;// [5,11]
		for (int i = 0; i < num; i++) {
			sb.append(ch[(int) (Math.random() * 63)]);// 首字母之外的值
		}
		return sb.toString();
	}

	/**
	 * @return
	 * 生成随机的电话号码
	 */
	public static String createRandomTelNum() {
		StringBuffer sb = new StringBuffer("1");
		sb.append((int) (Math.random() * 7) + 3);// [3-9]
		for (int i = 0; i < 9; i++) {
			sb.append((int) (Math.random() * 10));
		}
		return sb.toString();
	}

	/**
	 * @return
	 * 生成随机的积分 
	 */
	public static String createRandomScore() {
		StringBuffer sb = new StringBuffer();
		sb.append((int) (Math.random() * 9000) + 1000);
		return sb.toString();
	}

	/**
	 * @return
	 * 生成随机的会员卡号 
	 */
	public static String createRandomVipNum() {
		StringBuffer sb = new StringBuffer("t");
		for (int i = 0; i < 10; i++) {
			sb.append((int) (Math.random() * 10));
		}
		return sb.toString();
	}

	/**
	 * 生成随机的性别
	 * @return
	 * string类型的男或女 
	 */
	public static String createRandomGender() {
		if ((int) (Math.random() * 10) > 5) {
			return "女";
		}
		return "男";
	}

	/**
	 * 生成随机的邮箱
	 * @return
	 * String类型的随机邮箱
	 */
	public static String createRandomEamil() {
		StringBuffer sb = new StringBuffer();
		sb.append(ch[(int) (Math.random() * 52)]);// 首字符是字母
		for (int i = 0; i < 6; i++) {
			sb.append(ch[(int) (Math.random() * 63)]);
		}
		sb.append("@");
		for (int i = 0; i < 6; i++) {
			sb.append(ch[(int) (Math.random() * 62)]);
		}
		String[] st = { ".com.cn", ".com", ".cn" };
		sb.append(st[(int) (Math.random() * 3)]);
		return sb.toString();
	}

	/**
	 * 判断用户名是否符合要求的方法
	 * @param String类型 
	 * userName
	 * @return
	 * true或false
	 */
	public static boolean isUserNameOrPasswordisOk(String userName) {
		// userName:用户名，6-12位，字母开头，非空，唯一
		// passWord：密码，6-12位，字母开头，非空，
		String regex = "^[A-Za-z]\\w{5,11}$";
		// ^:表示正则表达式的开始
		// [A-Za-z]:大小写字母中的任意一个
		// \\w:大小写字母,0-9数字，下划线中的任意一个[a-zA-Z0-9_]
		// {5,11}:前面的组织单元出现5-11次
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userName);
		return matcher.matches();
	}

	/**
	 * 判断移动号码是否符合要求的方法
	 * @param telNum
	 * String类型的手机号码
	 * @return
	 * true 或  false
	 */
	public static boolean isPhoneNumisOk(String telNum) {
		// telNum:移动电话号码，11位，唯一，非空
		String regex = "^1[3456789]\\d{9}$";
		// ^:开头
		// 1:数字1
		// [3456789]:3-9任意一个
		// \\d:0-9任意一个
		// {9}:出现9次
		// $:结尾
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(telNum);
		return matcher.matches();
	}

	/**
	 * 判断会员卡号是否符合要求的方法
	 * @param cardNo
	 * String类型的会员卡号
	 * @return
	 * true 或 false
	 */
	public static boolean isVipIdisOk(String cardNo) {
		// cardNo:会员卡号，11位，单字母开头，其余数字，唯一，非空
		String regex = "^[A-Za-z]\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(cardNo);
		return matcher.matches();
	}

	/**
	 * 判断身份证号是否符合要求的方法
	 * @param identityNo
	 * String类型的 身份证
	 * @return
	 * true 或 false
	 */
	public static boolean isSfzisOk(String identityNo) {
		String regex = "^[1-7]\\d{5}(19|20)\\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
		// 身份证验证规则：41000119910101123X
		// 1、六位数字地址码，八位数字出生日期码，三位数字顺序码和一位校验码，可以用字母表示如为ABCDEFYYYYMMDDXXXR
		// ^:表示开头
		// [1-9]:表示1-9之间任意整数，4
		// \\d{5}:表示0-9之间任意数字出现5位，10001
		// (18|19|20):表示现在的年份只能选18、19、20开头，19
		// \\d{2}:表示年份，91
		// ((0[1-9])|(10|11|12)):表示月份，如果不满十位添加0，01
		// (([0-2][1-9])|10|20|30|31):表示月中的日，不满十位前面加0，01
		// \\d{3}:3位数字顺序码，123
		// [0-9Xx]:0-9整数Xx中的任意一个校验码，X
		// $:表示结尾
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(identityNo);
		return matcher.matches() && isSuitableDate(identityNo);
	}

	/**
	 * 生成随机的身份证号
	 * @return
	 * String 类型的身份证
	 */
	public static String createRandomSfzId() {
		StringBuffer sb = new StringBuffer();
		// [1-7]
		sb.append((int) (Math.random() * 7) + 1);// [1-7]
		// \\d{5}
		for (int i = 0; i < 5; i++) {
			sb.append((int) (Math.random() * 10));// \\d{5}
		}
		// (19|20)\\d{2}((0[1-9])
		int year = (int) (Math.random() * (2018 - 1900 + 1)) + 1900;
		sb.append(year);
		// (0[1-9])|10|11|12)
		int month = (int) (Math.random() * 12) + 1;
		if (month < 10) {
			sb.append("0" + month);
		} else {
			sb.append(month);
		}
		// (([0-2][1-9])|10|20|30|31)
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			int day = (int) (Math.random() * 31) + 1;
			if (day < 10) {
				sb.append("0" + day);
			} else {
				sb.append(day);
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			int day = (int) (Math.random() * 30) + 1;
			if (day < 10) {
				sb.append("0" + day);
			} else {
				sb.append(day);
			}
		} else {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				int day = (int) (Math.random() * 29) + 1;
				if (day < 10) {
					sb.append("0" + day);
				} else {
					sb.append(day);
				}
			} else {
				int day = (int) (Math.random() * 28) + 1;
				if (day < 10) {
					sb.append("0" + day);
				} else {
					sb.append(day);
				}
			}
		}
		// \\d{3}
		for (int i = 0; i < 3; i++) {
			sb.append((int) (Math.random() * 10));// \\d{3}
		}
		// [0-9Xx]
		int index = (int) (Math.random() * (61 - 50 + 1)) + 50;// [50-61]
		sb.append(ch[index]);
		return sb.toString();
	}

	/**
	 * 把身份证号转化成生日和年龄
	 * @param identityNo
	 * String类型的身份证号
	 * @return
	 * map<年龄,生日>
	 */
	public static Map<Integer, Date> changeIdentityToBirthAndAge(String identityNo) {
		Map<Integer, Date> map = new HashMap<Integer, Date>();
		if (identityNo == null) {
			return map;
		}
		// System.out.println(identityNo);
		String st = identityNo.substring(6, 14);// 1990-01-01
		// System.out.println(st);
		StringBuffer sb = new StringBuffer(st);
		sb.insert(4, "-");
		sb.insert(7, "-");
		st = sb.toString();// 1990-01-01
		// 日期格式化对象
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date bt = ft.parse(st);
			// System.out.println("bt=" + bt);
			// 获得日历类的对象
			Calendar cl = Calendar.getInstance();
			// 设置指定的日期
			cl.setTime(bt);
			// 获得指定日期的年
			int year = cl.get(Calendar.YEAR);
			// System.out.println(year);
			// 当前的年
			int now = Calendar.getInstance().get(Calendar.YEAR);
			// 年龄
			int age = 0;
			if (now == year) {
				age = 1;
			} else {
				age = now - year;
			}
			// System.out.println("age=" + age);
			map.put(age, bt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 验证email是否合法
	 * @param email
	 * @return
	 * true 或 false
	 */
	public static boolean isEmailisOk(String email) {
		// tom@126.com
		String regex = "^[A-Za-z]+\\w*@[a-zA-Z0-9]+\\.((com\\.cn)|com|cn)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * 判断身份证闰年和大小月是否在符合范围
	 * @param date
	 * @return
	 * true 或 false
	 */
	public static boolean isSuitableDate(String date) {
		try {
			String st = date.substring(6, 10);
			String st1 = date.substring(10, 12);
			String st2 = date.substring(12, 14);
			int year = Integer.parseInt(st);
			int month = Integer.parseInt(st1);
			int day = Integer.parseInt(st2);
			// 当前的年
			int now = Calendar.getInstance().get(Calendar.YEAR);
			// 小月
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				if (day > 30) {
					System.out.println(year + "小月只有30天");
					return false;
				}
			} else if (month == 2) {// 2月
				// 闰年
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					if (day > 29) {
						System.out.println(year + "2月只有29天");
						return false;
					}
				} else {
					// 不是闰年
					if (day > 28) {
						System.out.println(year + "2月只有28天");
						return false;
					}
				}
			}
			// 如果输入的年份超过当前年，判定不符合要求[1900-2018]
			if (year > now) {
				System.out.println(year + "年还没到");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	/**
	 * 将首字符大写
	 * @param str
	 * @return
	 * 大写后的字符
	 */
	public static String upperCase(String str) {
		// 先将字符串转换为字符数组
		char[] ch = str.toCharArray();
		// 将数组的第一个元素 即字符串首字母，进行ASCII 码前移，ASCII 中大写字母从65开始，小写字母从97开始，所以这里减去32。
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
	
	/**
	  * 把字符串首字母转化成大写
	  * @param str
	  * @return
	  */
	 public static String upperCaseFirstChar(String str) {
		try {
			return (str.charAt(0) + "").toUpperCase() + str.substring(1);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 生成随机的文件名称
	 * @param name
	 * @return
	 * 文件名称
	 */
	 public static String createFileName(String name) {
		 return enconding(UUID.randomUUID().toString()).substring(0, 10)
		 + name;
	 }

	/**
	 * 判断是否是图片
	 * @param fileName
	 * 文件名称
	 * @return
	 * ture 或 false
	 */
	public static boolean isPicisOk(String fileName) {
		String suffix = null;// 后缀tom.png
		if (fileName != null && !fileName.equals("")) {
			int index = fileName.lastIndexOf(".");
			suffix = fileName.substring(index + 1);// png
		}
		// 把"jpg", "bmp", "jpeg", "png", "gif"放到list中
		List<String> list = Arrays.asList("jpg", "bmp", "jpeg", "png", "gif");
		for (String string : list) {
			if (string.equalsIgnoreCase(suffix)) {
				return true;
			}
		}
		return false;
	}
	
	//public static Object createExcel;

	/**
	 * 创建excel 先创建后下载(downloadExcel)
	 * @param list
	 * 对象集合（excel数据）
	 * @param path
	 * 路径
	 * @return
	 * 1成功 0失败
	 */
	// 把list中的user对象读出来，写到excel中
//	public static int creatExcel(List<Cooperate> list, String path) {
//		// 1、指定目标文件
//		File target = new File(path);
//		OutputStream out = null;
//		XSSFWorkbook book = null;
//		try {
//			out = new FileOutputStream(target);
//			// 2、创建工作簿
//			book = new XSSFWorkbook();
//			// 3、创建工作簿中的页，指定页的名称
//			XSSFSheet sheet = book.createSheet("device");
//			// 4、创建行
//			int rowNum = 0;
//			XSSFRow row = sheet.createRow(rowNum++); 
//			// 5、创第一行的单元格并设置值
//			int coulumNum = 0;
//			row.createCell(coulumNum++).setCellValue("ip");
//			row.createCell(coulumNum++).setCellValue("基站名");
//			row.createCell(coulumNum++).setCellValue("imei");
//			row.createCell(coulumNum++).setCellValue("放电时长");
//			row.createCell(coulumNum++).setCellValue("备电时长");
//			row.createCell(coulumNum++).setCellValue("电池组在线数");
//			row.createCell(coulumNum++).setCellValue("工作状态");
//			row.createCell(coulumNum++).setCellValue("安装位置");
//			row.createCell(coulumNum++).setCellValue("版本号");
//			// 6、循环输出user对象
//			for (int i = 0; i < list.size(); i++) {
//				Cooperate cooperate = list.get(i);
//				coulumNum = 0;
//				row = sheet.createRow(rowNum++);
//				row.createCell(coulumNum++).setCellValue(cooperate.getIp());
//				row.createCell(coulumNum++).setCellValue(cooperate.getCooperate_name());
//				row.createCell(coulumNum++).setCellValue(cooperate.getCooperate_info().getImei());
//				row.createCell(coulumNum++).setCellValue(cooperate.getFdtime());
//				row.createCell(coulumNum++).setCellValue(cooperate.getTotaltime());
//				row.createCell(coulumNum++).setCellValue(cooperate.getOnlioncount());
//				row.createCell(coulumNum++).setCellValue(cooperate.getState());
//				row.createCell(coulumNum++).setCellValue(cooperate.getAddress().getProvince()+"-"+cooperate.getAddress().getCity());
//				row.createCell(coulumNum++).setCellValue(cooperate.getCooperate_info().getVersion());
//				//System.out.println(device); 
//
//			}
//			// 7、保存文件
//			book.write(out);
//			System.out.println("表创建成功!");
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// 8、关闭流
//			if (book != null) {
//				try {
//					book.close();
//				} catch (Exception e) {
//				}
//			}
//		}
//		return 0;
//	}

	/**
	 * 通用的创建excel表的方法
	 * @param <T>
	 * @param clazz
	 * 当前类名
	 * @param list
	 * 参数集合
	 * @param path
	 * 下载路径
	 * @return
	 * 1成功 0失败
	 */
	public static <T> int createExcels(Class<T> clazz, List<T> list, String path) {
		OutputStream out = null;
		XSSFWorkbook book = null;
		try {
			// 1、指定目标文件
			File target = new File(path);
			out = new FileOutputStream(target);
			// 2、创建工作簿
			book = new XSSFWorkbook(); 
			// 3、创建工作簿中的页，指定页的名称
			XSSFSheet sheet = book.createSheet(clazz.getSimpleName() + "device");
			// 4、创建第1行
			int rowNum = 0;
			XSSFRow row = sheet.createRow(rowNum++);
			Field[] field = clazz.getDeclaredFields();
			// 从1开始把序列号属性去除
			for (int i = 1; i < field.length; i++) {
				String name = field[i].getName();
				row.createCell(i).setCellValue(name);
			}
			// 5、遍历集合，拼接下一行
			for (int i = 0; i < list.size(); i++) {
				// 创建下一行
				row = sheet.createRow(rowNum++);
				// 遍历所有的属性
				for (int j = 1; j < field.length; j++) {
					// 获得属性的名称
					String name = field[j].getName();
					// 把属性名首字母转大写
					name = upperCaseFirstChar(name);
					// 获得集合的元素对象
					T t = list.get(i);
					// 执行当前类的方法get方法 
					Method mt = clazz.getDeclaredMethod("get" + name);
					// 获得当前getter方法的返回值类型
					Class<?> cl = mt.getReturnType();
					// 判断返回值类型，对单元格进行设置值
					String type = cl.getSimpleName();
					//System.out.println(type);
					if (type.equals("String")) {
						if (mt.invoke(t) == null) {
							row.createCell(j).setCellValue("");
						} else {
							String obj = (String) (mt.invoke(t));
							row.createCell(j).setCellValue(obj);
						}
					} else if (type.equals("Long")) {
						if (mt.invoke(t) == null) {
							row.createCell(j).setCellValue("");
						} else {
							Long obj = (Long) (mt.invoke(t));
							row.createCell(j).setCellValue(obj);
						}
					} else if (type.equals("Integer")) {
						if (mt.invoke(t) == null) {
							row.createCell(j).setCellValue("");
						} else {
							Integer obj = (Integer) (mt.invoke(t));
							row.createCell(j).setCellValue(obj);
						}
					} else if (type.equals("Date")) {
						if (mt.invoke(t) == null) {
							row.createCell(j).setCellValue("");
						} else {
							Date obj = (Date) (mt.invoke(t));
							row.createCell(j).setCellValue(obj);
						}
					} else {
						// 除此之外全部设置空
						row.createCell(j).setCellValue("");
					}
				}
			}
			// 6、保存文件
			book.write(out);
			System.out.println("创建表成功");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 7、关闭流
			if (book != null) {
				try {
					book.close();
				} catch (Exception e) {
				}
			}
		}
		return 0;
	}
	
	/**
	 * 下载成excel
	 * @param request
	 * @param response
	 * @param fileName
	 * 文件名-下载之后文件叫什么
	 * @param path
	 * 文件路径-下载之后存储的位置
	 * @return
	 * true成功 false失败
	 */
	// 下载方法
		public static Boolean downloadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, String path) {
			// 1、构建流的对象
			InputStream is = null;
			OutputStream out = null;
			// 2、设置响应头
			try {
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 3、设置响应的数据类型
			response.setContentType("multipart/form-data");
			// 4、边读边写
			byte[] bt = new byte[1024];
			int len;
			try {
				is = new FileInputStream(path);
				out = response.getOutputStream();
				while ((len = is.read(bt)) != -1) {
					out.write(bt, 0, len);
				}
				out.flush();
				System.out.println("表下载成功!");
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 5、关闭资源
				if (out != null) {
					try {
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 6、删除源文件
				new File(path).delete();
			}
			return false;
		}
		
	/**
	 * MD5加密
	 * @param msg
	 * 需要加密的字符串
	 * @return
	 * 加密后的String类型字符串
	 */
		public static String enconding(String msg) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 计算md5函数
				md.update(msg.getBytes());
				// digest()最后确定返回md5 hash值，返回值为8位字符串。
				// 因为md5 hash值是16位的hex值，实际上就是8位的字符
				// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；
				// 得到字符串形式的hash值转大写
				return new BigInteger(1, md.digest()).toString(16).toUpperCase();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 加密解密测试
		 * @param args
		 */
//		public static void main(String[] args) {
//			String[] arr= {"a","b","c","d","1","2","3"};
//			String s = "";
//			for(int i=0;i<20;i++) {
//				int j=(int) (Math.random()*6);
//				s=s+arr[j];
//			}
//			String md=encryptBASE64(s); 
//			System.out.println("md5加密:"+enconding(md));
//			System.out.println("加密后:"+md);
//			System.out.println("解密:"+decryptBASE64(md));
//		}

		/**
		 * BASE64的加密算法
		 * 
		 * @param target要加密的字符串
		 * @return String加密后的字符串
		 */
		public static String encryptBASE64(String target) {
			return Base64.encodeBytes(target.getBytes());
		}

		/**
		 * BASE64的解密算法
		 * 
		 * @param target要解密的字符串
		 * @return String解密后的字符串
		 */
		public static String decryptBASE64(String target) {
			try {
				return new String(Base64.decode(target), "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/**
		 * 分页内部类
		 * @author 曹天化
		 *
		 * @param 分页的对象
		 */
		static class Page<T> {
			private Integer pageNum;// 当前的页码
			private Integer totalCount;// 总条数，总记录数
			private Integer totalPage;// 总页数
			public static Integer pageCount = 12;// 每页15条     
			private Integer rowNum;// 当前页起始行号 
			private List<T> list;// 当前页的内容
			// Page类的设置顺序：
			// 1、setTotalCount()
			// 2、setTotalPage()
			// 3、setPageNum()
			// 4、setRowNum()

			public Integer getPageNum() {
				return pageNum;
			}

			public void setPageNum(Integer pageNum) {
				if (pageNum == null) {
					pageNum = 1;
				}
				if (pageNum < 1) {
					pageNum = 1;
				}
				if (pageNum > totalPage) {
					pageNum = totalPage;
				}
				if (totalPage == 0) {
					pageNum = 1;
				}
				this.pageNum = pageNum;
			}
			
			public static Integer getPageCount() {
				return pageCount;
			}

			public static void setPageCount(Integer pageCount) {
				Page.pageCount = pageCount;
			}

			public Integer getTotalCount() {
				return totalCount;
			}

			public void setTotalCount(Integer totalCount) {
				this.totalCount = totalCount;
			}

			public Integer getTotalPage() {
				return totalPage;
			}

			public void setTotalPage() {
				if (totalCount % pageCount == 0) {
					this.totalPage = totalCount / pageCount;
				} else {
					this.totalPage = totalCount / pageCount + 1;
				}
			}

			public Integer getRowNum() {
				return rowNum;
			}

			public void setRowNum() {
				this.rowNum = (pageNum - 1) * pageCount;
			}

			public List<T> getList() {
				return list;
			}

			public void setList(List<T> list) {
				this.list = list;
			}

			@Override
			public String toString() {
				return "Page [pageNum=" + pageNum + ", totalCount=" + totalCount + ", totalPage=" + totalPage + ", rowNum="
						+ rowNum + ", list=" + list + "]";
			}

			public void init(int totalCount, Integer pageNum) {
				setTotalCount(totalCount);
				setTotalPage();
				setPageNum(pageNum);
				setRowNum();
			}
		}
		
		/**
		 * 条形码工具类
		 *
		 * @author 曹天化
		 * @createDate 2019年11月14日
		 *
		 */
		static class BarcodeUtil {
		    /**
		     * 生成文件
		     *
		     * @param msg
		     * @param path
		     * @return
		     */
		    public static File createtiaoxm(String msg, String path) {
		        File file = new File(path);
		        try {
		            generate(msg, new FileOutputStream(file));
		        } catch (FileNotFoundException e) {
		            throw new RuntimeException(e);
		        }
		        return file;
		    }
		 
		    /**
		     * 生成字节
		     *
		     * @param msg
		     * @return
		     */
		    public static byte[] generate(String msg) {
		        ByteArrayOutputStream ous = new ByteArrayOutputStream();
		        generate(msg, ous);
		        return ous.toByteArray();
		    }
		 
		    /**
		     * 生成到流
		     *
		     * @param msg
		     * @param ous
		     */
		    public static void generate(String msg, OutputStream ous) {
		        if (StringUtils.isEmpty(msg) || ous == null) {
		            return;
		        }
		 
		        Code39Bean bean = new Code39Bean();
		 
		        // 精细度
		        final int dpi = 200; //越大图片越高
		        // module宽度
		        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
		 
		        // 配置对象
		        bean.setModuleWidth(moduleWidth);
		        bean.setWideFactor(3);
		        bean.doQuietZone(false);
		 
		        String format = "image/png";
		        try {
		 
		            // 输出到流
		            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
		                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
		 
		            // 生成条形码
		            bean.generateBarcode(canvas, msg);
		 
		            // 结束绘制
		            canvas.finish();
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
		    }
		    
		    //test
		    public static void main(String[] args) { 
		        String msg = "A-001-get";
		        String path = "C:/Users/Administrator/Desktop/barcode1.png";
		        createtiaoxm(msg, path);
		    }
		  
		}
		//以上生成条形码结束
		
		 /**
	     * 生成二维码（QR类型）
	     * 
	     * @param content
	     *            二维码文本内容
	     * @param file
	     *            生成的路径（文件路径）
	     * @return 返回文件路径加文件全名称 并执行
	     */
	    public static String createErweima(String content, String file) {
	        try {
	            if (null == content || content.equals("")) { 
	                log.error("CTHUtil.class-->getQRCode()-->content is null");
	            }
	            int width = 300; 
	            int height = 300;
	            HashMap<EncodeHintType, String> hints = new HashMap<>();
	            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	            // 二维码的格式是BarcodeFormat.QR_CODE qr类型二维码 BarcodeFormat.DATA_MATRIX dm码
	            BitMatrix qrc = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
	            File out = new File(file + "/" + createRandomFileName());//文件保存 位置 
	            // 生成二维码图片
	            WriteBitMatricToFile.writeBitMatricToFile(qrc, format, out);
	            WriteBitMatricToFile.parseCode(out); 
	            log.info(out.getAbsolutePath());
	            return out.getAbsolutePath();
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return null;
	        }
	    }
	    
	    /**
	     * 生成条形码 如果没效果就用 createtiaoxm
	     * 
	     * @param content
	     *            二维码文本内容
	     * @param file
	     *            生成的路径（文件路径）
	     * @return 返回文件路径加文件全名称
	     */
	    public static String createtiaoxma(String content, String file) {
	        try {
	            if (null == content || content.equals("")) {
	                log.error("CodeUtil.class-->getQRCode()-->content is null");
	                return null;
	            }
	            int len = content.trim().length();
	            if (len != 12 && len != 13) {
	                log.error("CodeUtil.class-->getQRCode()-->content length fail (12 or 13,fact " + len + ")");
	                return null;
	            }
	            int width = 105;
	            int height = 50;
	            HashMap<EncodeHintType, String> hints = new HashMap<>();
	            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	            // 条形码的格式是 BarcodeFormat.EAN_13
	            BitMatrix bc = new MultiFormatWriter().encode(content, BarcodeFormat.EAN_13, width, height, hints);
	            File out = new File(file + "/" + createRandomFileName());
	            // 输出图片
	            WriteBitMatricToFile.writeBitMatricToFile(bc, format, out);
	            WriteBitMatricToFile.parseCode(out);
	            // 记录日志
	            log.info(out.getAbsolutePath());
	            return out.getAbsolutePath();
	        } catch (Exception e) {
	            log.error(e.getMessage());
	            return null;
	        }
	    }

	    /**
	     * 生成随机文件名
	     * @return
	     * 随机文件名
	     */
	    private static String createRandomFileName() {
	        // 随机生成26以内的正整数
	        int next = (int) Math.floor(Math.random() * 26);
	        // 生成文件的名称，当前时间毫秒数+一个随机字母+后缀
	        return System.currentTimeMillis() + arr[next] + "." + format;
	    }

	    /**
	     * 解析二维码中的信息
	     * 
	     * @param filePath
	     * 二维码当前的路径
	     * @return 二维码中文本
	     */
	    public static String jiexierweima(String filePath) {
	        String content = "";
	        try {
	            File file = new File(filePath);
	            BufferedImage image = ImageIO.read(file);
	            LuminanceSource source = new BufferedImageLuminanceSource(image);
	            Binarizer binarizer = new HybridBinarizer(source);
	            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
	            Map<DecodeHintType, Object> hints = new HashMap<>();
	            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
	            MultiFormatReader formatReader = new MultiFormatReader();
	            Result result = formatReader.decode(binaryBitmap, hints);

	            System.out.println("result 为：" + result.toString());
	            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
	            System.out.println("resultText 为：" + result.getText());
	            // 设置返回值
	            content = result.getText();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return content;
	    }
	    
	    static class WriteBitMatricToFile {
	        private static final int BLACK = 0xFF000000;
	        private static final int WHITE = 0xFFFFFFFF;

	        private static BufferedImage toBufferedImage(BitMatrix bm) {
	            int width = bm.getWidth();
	            int height = bm.getHeight();
	            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
	            for (int i = 0; i < width; i++) {
	                for (int j = 0; j < height; j++) {
	                    image.setRGB(i, j, bm.get(i, j) ? BLACK : WHITE);
	                }
	            }
	            return image;
	        }

	        public static void writeBitMatricToFile(BitMatrix bm, String format, File file) {
	            BufferedImage image = toBufferedImage(bm);
	            try {
	                if (!ImageIO.write(image, format, file)) {
	                    throw new RuntimeException("Can not write an image to file" + file);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }

	        public static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
	            BufferedImage image = toBufferedImage(matrix);
	            if (!ImageIO.write(image, format, stream)) {
	                throw new IOException("Could not write an image of format " + format);
	            }
	        }

	        @SuppressWarnings({ "unchecked", "rawtypes" })
	        public static void parseCode(File file) {
	            try {
	                MultiFormatReader formatReader = new MultiFormatReader();

	                if (!file.exists()) {
	                    return;
	                }

	                BufferedImage image = ImageIO.read(file);

	                LuminanceSource source = new BufferedImageLuminanceSource(image);
	                Binarizer binarizer = new HybridBinarizer(source);
	                BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

	                Map hints = new HashMap();
	                hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

	                Result result = formatReader.decode(binaryBitmap, hints);

	                System.out.println("解析结果 = " + result.toString());
	                System.out.println("二维码格式类型 = " + result.getBarcodeFormat());
	                System.out.println("二维码文本内容 = " + result.getText());
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	   static class BufferedImageLuminanceSource extends LuminanceSource {

	        private final BufferedImage image;
	        private final int left;
	        private final int top;

	        public BufferedImageLuminanceSource(BufferedImage image) {
	            this(image, 0, 0, image.getWidth(), image.getHeight());
	        }

	        public BufferedImageLuminanceSource(BufferedImage image, int left, int top, int width, int height) {
	            super(width, height);

	            int sourceWidth = image.getWidth();
	            int sourceHeight = image.getHeight();
	            if (left + width > sourceWidth || top + height > sourceHeight) {
	                throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
	            }

	            for (int y = top; y < top + height; y++) {
	                for (int x = left; x < left + width; x++) {
	                    if ((image.getRGB(x, y) & 0xFF000000) == 0) {
	                        image.setRGB(x, y, 0xFFFFFFFF); // = white
	                    }
	                }
	            }

	            this.image = new BufferedImage(sourceWidth, sourceHeight, BufferedImage.TYPE_BYTE_GRAY);
	            this.image.getGraphics().drawImage(image, 0, 0, null);
	            this.left = left;
	            this.top = top;
	        }

	        @Override
	        public byte[] getRow(int y, byte[] row) {
	            if (y < 0 || y >= getHeight()) {
	                throw new IllegalArgumentException("Requested row is outside the image: " + y);
	            }
	            int width = getWidth();
	            if (row == null || row.length < width) {
	                row = new byte[width];
	            }
	            image.getRaster().getDataElements(left, top + y, width, 1, row);
	            return row;
	        }

	        @Override
	        public byte[] getMatrix() {
	            int width = getWidth();
	            int height = getHeight();
	            int area = width * height;
	            byte[] matrix = new byte[area];
	            image.getRaster().getDataElements(left, top, width, height, matrix);
	            return matrix;
	        }

	        @Override
	        public boolean isCropSupported() {
	            return true;
	        }

	        @Override
	        public LuminanceSource crop(int left, int top, int width, int height) {
	            return new BufferedImageLuminanceSource(image, this.left + left, this.top + top, width, height);
	        }

	        @Override
	        public boolean isRotateSupported() {
	            return true;
	        }

	        @Override
	        public LuminanceSource rotateCounterClockwise() {

	            int sourceWidth = image.getWidth();
	            int sourceHeight = image.getHeight();

	            AffineTransform transform = new AffineTransform(0.0, -1.0, 1.0, 0.0, 0.0, sourceWidth);

	            BufferedImage rotatedImage = new BufferedImage(sourceHeight, sourceWidth, BufferedImage.TYPE_BYTE_GRAY);

	            Graphics2D g = rotatedImage.createGraphics();
	            g.drawImage(image, transform, null);
	            g.dispose();

	            int width = getWidth();
	            return new BufferedImageLuminanceSource(rotatedImage, top, sourceWidth - (left + width), getHeight(), width);
	        }
	    }
	    
	   /**
	    * 获取本机的ip和本机名
	    * @return
	    */
	   public static List<String> getLocalIPList() {
	        List<String> ipList = new ArrayList<String>();
	        try {
	            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
	            NetworkInterface networkInterface;
	            Enumeration<InetAddress> inetAddresses;
	            InetAddress inetAddress;
	            String ip;
	            while (networkInterfaces.hasMoreElements()) {
	                networkInterface = networkInterfaces.nextElement();
	                inetAddresses = networkInterface.getInetAddresses();
	                while (inetAddresses.hasMoreElements()) {
	                    inetAddress = inetAddresses.nextElement();
	                    if (inetAddress != null && inetAddress instanceof Inet4Address) { // IPV4
	                        ip = inetAddress.getHostAddress();
	                        ipList.add(ip);
	                    }
	                }
	            }
	        } catch (SocketException e) {
	            e.printStackTrace();
	        }
			return ipList;
		}
	   //以上生成二维码结束
//	    public static void main(String[] args) {
//	    	createErweima("仓库-A区-取", "C:/Users/Administrator/Desktop");
//	    	createtiaoxma("仓库-A区-取", "C:/Users/Administrator/Desktop");
//		}
}

