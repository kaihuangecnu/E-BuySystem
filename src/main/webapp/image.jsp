<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.awt.*" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="image/jpeg" pageEncoding="UTF-8" %>
<%!
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
%>
<%
    out.clear();    //这句针对resin服务器，如果是Tomcat服务器则不需要这句
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires",0);
    int width = 60, height = 20;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics graphics = image.getGraphics();
    Random random = new Random();
    graphics.setColor(getRandColor(200, 250));
    graphics.fillRect(0, 0, width, height);
    graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    graphics.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(12);
        int yl = random.nextInt(12);
        graphics.drawLine(x, y, x + xl, y + yl);
    }
    String sRand = "";
    for (int i = 0; i < 4; i++) {
        String rand = String.valueOf(random.nextInt(10));
        sRand += rand;
        graphics.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
        graphics.drawString(rand, 13 * i + 6, 16);
    }
    // 将认证码存入Session范围
    session.setAttribute("sRand", sRand);
    graphics.dispose();
	//out.clear();
    ImageIO.write(image,"JPEG", response.getOutputStream());
%>