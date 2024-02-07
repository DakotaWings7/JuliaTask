import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class JuliaFractal {
    private final int width;
    private final int height;
    private final double realPart;
    private final double imaginaryPart;

    public JuliaFractal(int width, int height, double realPart, double imaginaryPart) {
        this.width = width;
        this.height = height;
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public BufferedImage generateFractal() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int y = 0; y < height; y++) {
            final int row = y;
            executor.execute(() -> {
                for (int x = 0; x < width; x++) {
                    int color = calculateJuliaPixel(x, row);
                    image.setRGB(x, row, color);
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        return image;
    }

    private int calculateJuliaPixel(int x, int y) {
        double zx = 1.5 * (x - (double) width / 2) / (0.5 * width);
        double zy = (y - (double) height / 2) / (0.5 * height);

        int iteration = 0;
        int maxIterations = 300;

        while (zx * zx + zy * zy < 4 && iteration < maxIterations) {
            double temp = zx * zx - zy * zy + realPart;
            zy = 2.0 * zx * zy + imaginaryPart;
            zx = temp;
            iteration++;
        }

        return getColor(iteration, maxIterations);
    }

    private int getColor(int iteration, int maxIterations) {
        if (iteration == maxIterations) {
            return 0x000000;
        } else {
            float hue = (float) iteration / maxIterations;
            return java.awt.Color.HSBtoRGB(hue, 1f, 1f);
        }
    }

    public void saveImage(BufferedImage image, String outputFilePath) {
        try {
            File outputfile = new File(outputFilePath);
            ImageIO.write(image, "png", outputfile);
            System.out.println("Изображение сохранено в " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении изображения.");
        }
    }
}