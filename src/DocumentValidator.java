import java.io.*;
import java.util.*;

public class DocumentValidator {
    public static void main(String[] args) {
        List<String> fileList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;

        while (true) {
            try {
                fileName = reader.readLine();
                if (fileName.equals("0")) break;
                fileList.add(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Set<String> documentNumbers = new HashSet<>();

        Map<String, String> documentStatusMap = new HashMap<>();

        for (String file : fileList) {
            try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                String documentNumber;
                while ((documentNumber = fileReader.readLine()) != null) {
                    if (!documentNumbers.contains(documentNumber)) {
                        documentNumbers.add(documentNumber);
                        boolean isValid = false;
                        if (documentNumber.startsWith("docnum")) {
                            isValid = documentNumber.length() == 15;
                        } else if (documentNumber.startsWith("kontract")) {
                            isValid = documentNumber.length() == 17;
                        }
                        if (isValid) {
                            documentStatusMap.put(documentNumber, "валиден");
                        } else {
                            documentStatusMap.put(documentNumber, "не валиден");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("отчет.txt"))) {
            for (Map.Entry<String, String> entry : documentStatusMap.entrySet()) {
                writer.write(entry.getKey() + " - " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


