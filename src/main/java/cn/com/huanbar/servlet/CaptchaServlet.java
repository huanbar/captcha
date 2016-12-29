package cn.com.huanbar.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.huanbar.util.ChengyuCaptchaHelper;

public class CaptchaServlet extends HttpServlet {
	private static Logger logger = LoggerFactory.getLogger(CaptchaServlet.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("s");
		
		BufferedImage bufimg = ChengyuCaptchaHelper.getImageChallenge(s);
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-chche");
		response.setDateHeader("Expires", 0L);
		response.setContentType("image/jpeg");
		try {
			ImageIO.write(bufimg, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			logger.error("输出验证码给用户时发生异常:" + e.getMessage());
		}

	}
}
