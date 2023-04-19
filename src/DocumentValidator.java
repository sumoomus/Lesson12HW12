
    import java.io.*;
import java.util.*;

    public class DocumentValidator {
        public static void main(String[] args) {
            List<String> fileList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName;

            // Считываем имена файлов с консоли
            while (true) {
                try {
                    fileName = reader.readLine();
                    if (fileName.equals("0")) break;
                    fileList.add(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Создаем множество для хранения номеров документов
            Set<String> documentNumbers = new HashSet<>();

            // Создаем карту для хранения информации о номерах документов и их статусе (валидный/не валидный)
            Map<String, String> documentStatusMap = new HashMap<>();

            // Обработка каждого документа
            for (String file : fileList) {
                try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    String documentNumber;
                    while ((documentNumber = fileReader.readLine()) != null) {
                        // Проверяем, был ли уже обработан документ с таким номером
                        if (!documentNumbers.contains(documentNumber)) {
                            // Добавляем номер документа в множество
                            documentNumbers.add(documentNumber);
                            // Проверяем валидность номера документа
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

            // Создаем файл отчета и записываем в него информацию о номерах документов и их статусе
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

}
