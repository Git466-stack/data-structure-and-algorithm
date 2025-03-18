public class CompareStringBufferAndStringBuilder {
    public static void main(String[] args) {
        // Define the number of concatenations
        int numberOfConcatenations = 1_000_000;
        String text = "hello";

        // Measure performance for StringBuffer
        StringBuffer stringBuffer = new StringBuffer();
        long startTimeBuffer = System.nanoTime();
        for (int i = 0; i < numberOfConcatenations; i++) {
            stringBuffer.append(text);
        }
        long endTimeBuffer = System.nanoTime();
        long timeTakenBuffer = endTimeBuffer - startTimeBuffer;

        // Measure performance for StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        long startTimeBuilder = System.nanoTime();
        for (int i = 0; i < numberOfConcatenations; i++) {
            stringBuilder.append(text);
        }
        long endTimeBuilder = System.nanoTime();
        long timeTakenBuilder = endTimeBuilder - startTimeBuilder;

        // Print the results
        System.out.println("Time taken by StringBuffer: " + timeTakenBuffer / 1_000_000.0 + " ms");
        System.out.println("Time taken by StringBuilder: " + timeTakenBuilder / 1_000_000.0 + " ms");
    }
}
