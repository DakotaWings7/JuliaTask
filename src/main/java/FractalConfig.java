
class FractalConfig {
    private int width;
    private int height;
    private double realPart;
    private double imaginaryPart;
    private String outputFilePath;

    public FractalConfig(int width, int height, double realPart, double imaginaryPart, String outputFilePath) {
        this.width = width;
        this.height = height;
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
        this.outputFilePath = outputFilePath;
    }

    public static FractalConfig parseArgs(String[] args) {
        int width = 0, height = 0;
        double realPart = 0, imaginaryPart = 0;
        String outputFilePath = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                String[] dimensions = args[++i].split(";");
                width = Integer.parseInt(dimensions[0]);
                height = Integer.parseInt(dimensions[1]);
            } else if (args[i].equals("-c")) {
                String[] constants = args[++i].split(";");
                realPart = Double.parseDouble(constants[0]);
                imaginaryPart = Double.parseDouble(constants[1]);
            } else if (args[i].equals("-o")) {
                outputFilePath = args[++i];
            }
        }

        return new FractalConfig(width, height, realPart, imaginaryPart, outputFilePath);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getRealPart() {
        return realPart;
    }

    public double getImaginaryPart() {
        return imaginaryPart;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }
}
