package cn.com.huanbar.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 成语验证码生成器
 * 
 * @author zhou
 *
 */
public class ChengyuCaptchaHelper {

	public static Random random = new Random();
	public static String CAPTCHAPED = "_captcha";
	public static String R = "R";
	public static String G = "G";
	public static String B = "B";
	public static String ISTRUE = "true";
	// 验证码配置项
	public static String[] chengyus = "九霄云外, 腾云驾雾,壮志凌云 ,风云变幻 ,风起云涌 ,行云流水 ,风卷残云,浮云蔽日,孤云野鹤 ,烘云托月,过眼云烟,烟消云散 ,春光明媚,万紫千红,春雨如油,生机勃勃,春色满园,春意盎然 ,鸟语花香,春暖花开,百花齐放,和风细雨,一身正气 ,临危不惧 ,光明磊落 ,堂堂正正 ,大智大勇,力挽狂澜,急中生智,仰不愧天,镇定自若 ,化险为夷"
			.split(",");// 成语数组
	public static String colorPrimary = "r";// 成语颜色基调
	public static int colorPrimaryValue = 91;// 成语颜色基调值
	public static int colorMin = 190;// 其他颜色最小值
	public static int colorMax = 220;// 其他颜色最大值
	public static String[] fonts = "黑体".split(",");
	public static int lineCount = 50;// 干扰线数量
	public static String lineColorPrimary = "r";// 干扰线颜色基调
	public static int lineColorMin = 190;// 干扰线其他颜色最小值
	public static int lineColorMax = 220;// 干扰线其他颜色最小值
	public static int width = 150;// 验证码长度
	public static int height = 40;// 验证码宽度
	public static int rotate = 5;// 旋转角度范围
	public static int shift = 3;// 字符间隔

	public static BufferedImage getImageChallenge() {
		String chengyu = String.valueOf(chengyus[random.nextInt(chengyus.length)]).trim();
		return getImageChallenge(chengyu, chengyu.length(), width, height, lineCount);
	}

	/**
	 * 
	 * @param chengyu
	 *            成语
	 * @param width
	 *            图片宽
	 * @param height
	 *            图片高
	 * @param codeCount
	 *            字符个数
	 * @param lineCount
	 *            干扰线条数
	 */
	public static BufferedImage getImageChallenge(String chengyu, int codeCount, int width, int height, int lineCount) {
		int x = 0, fontHeight = 0, codeY = 0;

		x = width / (codeCount + shift);// 每个字符的宽度
		fontHeight = height - 2;// 字体的高度
		codeY = height - 4;

		// 图像
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		// 图像设置为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 设置字体
		Font font = getFont(fontHeight);
		g.setFont(font);

		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width / 8);
			int ye = ys + random.nextInt(height / 8);

			g.setColor(getRandColor(lineColorMin, lineColorMax, lineColorPrimary));
			g.drawLine(xs, ys, xe, ye);
		}
		// 缺失文字位置
		int ci = random.nextInt(chengyu.length());
		if (ci == 0) {
			ci = 1;
		}

		// 生成验证码。
		for (int i = 0; i < chengyu.length(); i++) {
			String strRand = String.valueOf(chengyu.charAt(i));
			if (ci == i) {
				strRand = "?";
			}
			// 设置字符的颜色值
			g.setColor(getRandColor(colorMin, colorMax, colorPrimary));

			if (rotate != 0) {// 旋转字体
				g.translate(i * x + 6, codeY + 2);
				g.rotate(Math.toRadians(rotate - random.nextInt(rotate * 2)), width / 2, height / 2);
				g.drawString(strRand, 0, 0);
				g.translate(-(i * x + 6), -(codeY + 2));
			} else {
				g.drawString(strRand, i * x + 8, codeY);
			}
		}
		return buffImg;

	}

	/**
	 * 产生随机字体
	 */
	public static Font getFont(int size) {
		return new Font(fonts[random.nextInt(fonts.length)], Font.PLAIN, size);
	}

	/**
	 * 得到随机颜色
	 */
	public static Color getRandColor(int min, int max, String rgb) {// 给定范围获得随机颜色
		if (min > 255) {
			min = 255;
		} else if (min < 0) {
			min = 0;
		}
		if (max > 255) {
			max = 255;
		} else if (max < 0) {
			max = 0;
		}
		int q1 = min + random.nextInt(max - min);
		int q2 = min + random.nextInt(max - min);
		if (R.equals(rgb.toUpperCase())) {
			return new Color(colorPrimaryValue, q1, q2);
		} else if (G.equals(rgb.toUpperCase())) {
			return new Color(q1, colorPrimaryValue, q2);
		} else if (B.equals(rgb.toUpperCase())) {
			return new Color(q1, q2, colorPrimaryValue);
		}
		return new Color(random.nextInt(251), random.nextInt(251), random.nextInt(251));
	}

	public static BufferedImage getImageChallenge(String s) {

		String chengyu = String.valueOf(chengyus[random.nextInt(chengyus.length)]).trim();
		return getImageChallenge(chengyu, chengyu.length(), width, height, lineCount);
	}

}
