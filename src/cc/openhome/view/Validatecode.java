package cc.openhome.view;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


@WebServlet("/validatecode")
public class Validatecode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        //禁止页面缓存
        response.setHeader("Prama","No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");  //设置相应正文的MIME类型图片
        int width = 60, height = 20;
        /*
        	创建一个位于缓冲区的图像，宽度60，高度20
         */
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18)); // 字体格式
        g.setColor(getRandomColor(160,200));
        for (int i=0; i<130; i++){
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            int x1=random.nextInt(12);
            int y1=random.nextInt(12);
            g.drawLine(x,y,x+x1,y+y1);  //在图像的坐标(x,y)和坐标（x+x1, y+y1）之间画干扰线
        }
        String strCode="";
        for (int i=0; i<=3; i++){
            String strNumber = String.valueOf(random.nextInt(10));
            strCode += strNumber;
            //设置字体颜色
            g.setColor(new Color(15+random.nextInt(120), 15+random.nextInt(120), 15+random.nextInt(120)));
            g.drawString(strNumber, 13*i+6, 16);
        }
        request.getSession().setAttribute("Code",strCode);  //把验证码保存到session中
        g.dispose();                                        //释放图像的上下文以及使用的所有系统资源
        ImageIO.write(image,"JPEG",response.getOutputStream()); //输出JPEG格式的图像
        response.getOutputStream().flush();                      //刷新输出流
        response.getOutputStream().close();                      //关闭输出流
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public Color getRandomColor(int fc, int bc){
        Random random = new Random();
        Color randomColor = null;
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;

        //设置0~255之间的随机颜色值
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        randomColor = new Color(r,g,b);
        return randomColor;
    }
}
