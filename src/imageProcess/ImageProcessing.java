package imageProcess;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
import java.lang.Math;

public class ImageProcessing {
	public static void main(String[] args) {
		// The provided images are apple.jpg, flower.jpg, and kitten.jpg
		int[][] imageData = imgToTwoD("images/apple.jpg");
		// Or load your own image using a URL!
		// viewImageData(imageData);
		// int[][] trimmed = trimBorders(imageData, 60);
		// twoDToImage(trimmed, "out/trimmed_kitten.jpg");
		// int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
		// Painting with pixels
		
		/* 
		 * negativeImage method
		 */
		// int[][] negativeImage = negativeColor(imageData);
		// twoDToImage(negativeImage, "out/negative-kitten.jpg");
		
		/* 
		 * stretchHorizontally method
		 */
		// int[][] negativeImage = stretchHorizontally(imageData);
		// twoDToImage(negativeImage, "out/stretchHorizontally-kitten.jpg");
		
		/* 
		 * shrinkVertically method
		 */
		// int[][] shrinkImage = shrinkVertically(imageData);
		// twoDToImage(shrinkImage, "out/shrinkVertically-kitten.jpg");
		
		/* 
		 * invertImage method
		 */
		// int[][] invert = invertImage(imageData);
		// twoDToImage(invert, "out/invertImage-kitten.jpg");	
		
		/*
		 * colorFilter method
		 */
		// int[][] colorFilterImage = colorFilter(imageData, -75, 30, -30);
		// twoDToImage(colorFilterImage, "out/colorFilterImage-apple.jpg");
		
		/*
		 * paintRandomImage method
		 */
		// int[][] myCanvas = new int[500][500];
		// int[][] randomImage = paintRandomImage(myCanvas);
		// twoDToImage(randomImage, "out/randomImage.jpg");
		
		/*
		 * paintRectangle method
		 */
		int rowsC = 800;
		int colsC = 800;
		
		int height = 100;
		int width = 200;
		
		int[][] myCanvas = new int[rowsC][colsC];
		int[] color = {255, 255, 0, 255};

		// int[][] imageWithRectangle = paintRectangle(myCanvas, width, height, (int) rowsC / 2 - height / 2, (int) colsC / 2 - width / 2, getColorIntValFromRGBA(color));
		int[][] imageWithRectangle = paintRectangle(myCanvas, width, height, 200, 100, getColorIntValFromRGBA(color));
		twoDToImage(imageWithRectangle, "out/paintRectangle.jpg");
		
	}
	// Image Processing Methods
	public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
		// Example Method
		if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
			int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
			for (int i = 0; i < trimmedImg.length; i++) {
				for (int j = 0; j < trimmedImg[i].length; j++) {
					trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
				}
			}
			return trimmedImg;
		} else {
			System.out.println("Cannot trim that many pixels from the given image.");
			return imageTwoD;
		}
	}
	
	public static int[][] negativeColor(int[][] imageTwoD) {
		int rows = imageTwoD.length;
	    int cols = imageTwoD[0].length;
	
	    int[][] negativeImageTwoD = new int[rows][cols];
	    for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < cols; j++) {
	    	  int[] pixelColor = getRGBAFromPixel(imageTwoD[i][j]);
	    	  pixelColor[0] = 255 - pixelColor[0];
	    	  pixelColor[1] = 255 - pixelColor[1];
	    	  pixelColor[2] = 255 - pixelColor[2];
	    	  
	    	  negativeImageTwoD[i][j] = getColorIntValFromRGBA(pixelColor);
	      }
	    }

		return negativeImageTwoD;
	}
	
	public static int[][] stretchHorizontally(int[][] imageTwoD) {
		int rows = imageTwoD.length;
		int cols = imageTwoD[0].length;
		
		int[][] stretchedImageTwoD = new int[rows][cols * 2];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				stretchedImageTwoD[i][j * 2] = imageTwoD[i][j];
				stretchedImageTwoD[i][j * 2 + 1] = imageTwoD[i][j];
			}
		}
		
		return stretchedImageTwoD;
	}
	
	public static int[][] shrinkVertically(int[][] imageTwoD) {
		int rows = (int) imageTwoD.length / 2;
		int cols = imageTwoD[0].length;
		
		int[][] shrinkedImageTwoD = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				shrinkedImageTwoD[i][j] = imageTwoD[i * 2][j];
			}
		}
		
		return shrinkedImageTwoD;
	}
	
	public static int[][] invertImage(int[][] imageTwoD) {
		int rows = imageTwoD.length;
		int cols = imageTwoD[0].length;
		
		int[][] invertImageTwoD = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int oppositeRowPixel = rows - 1 - i;
				int oppositeColPixel = cols - 1 - j;
				
				invertImageTwoD[i][j] = imageTwoD[oppositeRowPixel][oppositeColPixel];
			}
		}
		
		return invertImageTwoD;
	}
	
	public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
		int rows = imageTwoD.length;
		int cols = imageTwoD[0].length;
		
		int[][] filteredImageTwoD = new int[rows][cols];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int[] pixelColor = getRGBAFromPixel(imageTwoD[i][j]);
				
				pixelColor[0] += redChangeValue;
				pixelColor[1] += greenChangeValue;
				pixelColor[2] += blueChangeValue;
				
				if (pixelColor[0] < 0) {
					pixelColor[0] = 0;
				} else if (pixelColor[0] > 255) {
					pixelColor[0] = 255;
				}
				
				if (pixelColor[1] < 0) {
					pixelColor[1] = 0;
				} else if (pixelColor[1] > 255) {
					pixelColor[1] = 255;
				}
				
				if (pixelColor[2] < 0) {
					pixelColor[2] = 0;
				} else if (pixelColor[2] > 255) {
					pixelColor[2] = 255;
				}
				
				filteredImageTwoD[i][j] = getColorIntValFromRGBA(pixelColor);		
			}
		}
		
		return filteredImageTwoD;
	}
	
	// Painting Methods
	public static int[][] paintRandomImage(int[][] canvas) {
		Random rand = new Random();
		
		for (int i = 0; i < canvas.length; i++) {
			for (int j = 0; j < canvas[i].length; j++) {
				int[] randomColor = {rand.nextInt(256), rand.nextInt(256), rand.nextInt(256), 255};
				
				canvas[i][j] = getColorIntValFromRGBA(randomColor);
			}
		}
		
		return canvas;
	}
	
	public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
		int rows = canvas.length;
		int cols = canvas[0].length;
		
		for (int i = rowPosition; i < rowPosition + height && i < rows; i++) {
			for (int j = colPosition; j < colPosition + width && j < cols; j++) {
				if (j < cols) {
					if (i < rows) {
						canvas[i][j] = color;
					}
				}
			}
		}

		return canvas;
	}
	
	public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
		// TODO: Fill in the code for this method
		return null;
	}
	
	// Utility Methods
	public static int[][] imgToTwoD(String inputFileOrLink) {
		try {
			BufferedImage image = null;
			if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
				URL imageUrl = new URL(inputFileOrLink);
				image = ImageIO.read(imageUrl);
				if (image == null) {
					System.out.println("Failed to get image from provided URL.");
				}
			} else {
				image = ImageIO.read(new File(inputFileOrLink));
			}
			int imgRows = image.getHeight();
			int imgCols = image.getWidth();
			int[][] pixelData = new int[imgRows][imgCols];
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					pixelData[i][j] = image.getRGB(j, i);
				}
			}
			return pixelData;
		} catch (Exception e) {
			System.out.println("Failed to load image: " + e.getLocalizedMessage());
			return null;
		}
	}
	public static void twoDToImage(int[][] imgData, String fileName) {
		try {
			int imgRows = imgData.length;
			int imgCols = imgData[0].length;
			BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					result.setRGB(j, i, imgData[i][j]);
				}
			}
			File output = new File(fileName);
			ImageIO.write(result, "jpg", output);
		} catch (Exception e) {
			System.out.println("Failed to save image: " + e.getLocalizedMessage());
		}
	}
	public static int[] getRGBAFromPixel(int pixelColorValue) {
		Color pixelColor = new Color(pixelColorValue);
		return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
	}
	public static int getColorIntValFromRGBA(int[] colorData) {
		if (colorData.length == 4) {
			Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
			return color.getRGB();
		} else {
			System.out.println("Incorrect number of elements in RGBA array.");
			return -1;
		}
	}
	public static void viewImageData(int[][] imageTwoD) {
		if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
			int[][] rawPixels = new int[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rawPixels[i][j] = imageTwoD[i][j];
				}
			}
			System.out.println("Raw pixel data from the top left corner.");
			System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
			int[][][] rgbPixels = new int[3][3][4];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
				}
			}
			System.out.println();
			System.out.println("Extracted RGBA pixel data from top the left corner.");
			for (int[][] row : rgbPixels) {
				System.out.print(Arrays.deepToString(row) + System.lineSeparator());
			}
		} else {
			System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
		}
	}
}