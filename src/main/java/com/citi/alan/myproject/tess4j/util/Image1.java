package com.citi.alan.myproject.tess4j.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Image1 {

	private void setAlpha(String os) {
		/**
		 * 增加测试项 读取图片，绘制成半透明,修改像素
		 */
		try {
			ImageIcon imageIcon = new ImageIcon(os);
			BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
			Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
			g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
			// 循环每一个像素点，改变像素点的Alpha值
			int alpha = 100;
			List<String> allRGBs = new ArrayList<String>();
			for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
				for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
					int pixel = bufferedImage.getRGB(j2, j1);
					int[] rgb = new int[3];
					rgb[0] = (pixel & 0xff0000) >> 16;
					rgb[1] = (pixel & 0xff00) >> 8;
					rgb[2] = (pixel & 0xff);
					String value = rgb[0] + "," + rgb[1] + "," + rgb[2];
					if (!allRGBs.contains(value)) {
						System.out.println("i=" + j1 + ",j=" + j2 + ":(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ")");
						allRGBs.add(value);
					}

					pixel = ((alpha + 1) << 24) | (pixel & 0x00ffffff);
					bufferedImage.setRGB(j2, j1, pixel);
				}
			}
			g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());

			// 生成图片为PNG
			ImageIO.write(bufferedImage, "png", new File("/Users/gongjiao/pic/my.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * @param imagePath 要灰化图像的名字
     * @param path 生成的图像的名字
     * 
     */
    public void transformGray_R(String imagePath, String path) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
            for(int y = image.getMinY(); y  < image.getHeight(); y++) {
                for(int x = image.getMinX(); x < image.getWidth(); x ++) {
                    int pixel = image.getRGB(x, y);
                    int r = (pixel >> 16) & 0x00ff;
                    pixel = (r & 0x000000ff) | (pixel & 0xffffff00); //用r的值设置b的值
                    pixel = ((r<<8) & 0x0000ff00) | (pixel & 0xffff00ff);//用r的值设置g的值
                    image.setRGB(x, y, pixel);
                }
            }
            try {
                ImageIO.write(image, "jpg", new File(path));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }
    
    public void transformGrayJiaQuan (String imagePath, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            int width = image.getWidth();
            int height = image.getHeight();
            for(int y = image.getMinY(); y < height; y++) {
                for(int x = image.getMinX(); x < width ; x ++) {
                    int pixel = image.getRGB(x, y);
                    int r = (pixel >> 16) & 0xff;
                    int g = (pixel >> 8) & 0xff;
                    int b = pixel & 0xff;
                    //加权法的核心,加权法是用图片的亮度作为灰度值的
                    int grayValue = (int) (0.30f * r + 0.59f * g + 0.11f * b ); 
                    //int grayValue = (int) (0.21f * 4 + 0.71f * g + 0.08f * b); //还可以使用这个系数的加权法
                    pixel = (grayValue << 16) & 0x00ff0000 | (pixel & 0xff00ffff);
                    pixel = (grayValue << 8) & 0x0000ff00 | (pixel & 0xffff00ff    );
                    pixel = (grayValue) & 0x000000ff | (pixel & 0xffffff00);
                    image.setRGB(x, y, pixel);
                }
            }
            
            ImageIO.write(image, "jpg", new File(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private static int colorToRGB(int alpha, int red, int green, int blue) {
    	  
    	  int newPixel = 0;
    	  newPixel += alpha;
    	  newPixel = newPixel << 8;
    	  newPixel += red;
    	  newPixel = newPixel << 8;
    	  newPixel += green;
    	  newPixel = newPixel << 8;
    	  newPixel += blue;
    	  
    	  return newPixel;
    	  
    	}

//	public static void main(String[] args) throws Exception {
//		int x = 0;
//		Image1 rc = new Image1();
//		rc.transformGray_R("/Users/gongjiao/pic/weixin2.png", "/Users/gongjiao/pic/xx.png");
//	}
    
    public static void main(String[] args) throws IOException {
    	 BufferedImage bufferedImage = ImageIO.read(new File("/Users/gongjiao/pic/weixin2.png"));
    	 BufferedImage grayImage = 
    	  new BufferedImage(bufferedImage.getWidth(), 
    	       bufferedImage.getHeight(), 
    	       bufferedImage.getType());
    	    
    	   
    	 for (int i = 0; i < bufferedImage.getWidth(); i++) {
    	  for (int j = 0; j < bufferedImage.getHeight(); j++) {
    	   final int color = bufferedImage.getRGB(i, j);
    	   final int r = (color >> 16) & 0xff;
    	   final int g = (color >> 8) & 0xff;
    	   final int b = color & 0xff;
    	   int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);;
    	   //System.out.println(i + " : " + j + " " + gray);
    	   int newPixel = colorToRGB(255, gray, gray, gray);
    	   grayImage.setRGB(i, j, newPixel);
    	  }
    	 }
    	 File newFile = new File("/Users/gongjiao/pic" + "/ok.jpg");
    	 ImageIO.write(grayImage, "jpg", newFile);
    	}

}