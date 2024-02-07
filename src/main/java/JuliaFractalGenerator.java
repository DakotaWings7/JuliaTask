
import java.awt.image.BufferedImage;

public class JuliaFractalGenerator {

    public static void main(String[] args) {
        FractalConfig config = FractalConfig.parseArgs(args);

        JuliaFractal fractal = new JuliaFractal(config.getWidth(), config.getHeight(), config.getRealPart(), config.getImaginaryPart());
        BufferedImage image = fractal.generateFractal();

        fractal.saveImage(image, config.getOutputFilePath());
    }
}