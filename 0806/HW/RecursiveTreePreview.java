import java.util.*;

public class RecursiveTreePreview {
    public static void main(String[] args) {
        // 1. 模擬檔案樹結構
        Folder root = new Folder("root");
        root.addFile("file1.txt");
        Folder sub1 = new Folder("docs");
        sub1.addFile("a.docx");
        sub1.addFile("b.docx");
        Folder sub2 = new Folder("images");
        sub2.addFile("img1.jpg");
        Folder subsub = new Folder("subimg");
        subsub.addFile("img2.png");
        sub2.addFolder(subsub);
        root.addFolder(sub1);
        root.addFolder(sub2);

        System.out.println("1. 📁 總檔案數：" + countFiles(root));
        System.out.println("\n2. 📋 選單結構：");
        printFolder(root, 0);

        // 3. 巢狀陣列展平
        List<Object> nested = Arrays.asList(1, Arrays.asList(2, 3), Arrays.asList(4, Arrays.asList(5, 6)));
        System.out.println("\n3. 🔄 展平後的陣列：" + flatten(nested));

        // 4. 巢狀清單最大深度
        System.out.println("4. ⬇️ 巢狀清單最大深度：" + maxDepth(nested));
    }

    // ------------ 功能 1 & 2：資料夾樹 ------------

    static class Folder {
        String name;
        List<String> files = new ArrayList<>();
        List<Folder> subfolders = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }

        void addFile(String fileName) {
            files.add(fileName);
        }

        void addFolder(Folder folder) {
            subfolders.add(folder);
        }
    }

    // 1. 遞迴計算總檔案數
    public static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder sub : folder.subfolders) {
            count += countFiles(sub);
        }
        return count;
    }

    // 2. 遞迴列印多層選單結構
    public static void printFolder(Folder folder, int indent) {
        System.out.println("  ".repeat(indent) + "📁 " + folder.name);
        for (String file : folder.files) {
            System.out.println("  ".repeat(indent + 1) + "📄 " + file);
        }
        for (Folder sub : folder.subfolders) {
            printFolder(sub, indent + 1);
        }
    }

    // ------------ 功能 3：展平巢狀陣列 ------------

    public static List<Integer> flatten(List<Object> nestedList) {
        List<Integer> result = new ArrayList<>();
        for (Object elem : nestedList) {
            if (elem instanceof Integer) {
                result.add((Integer) elem);
            } else if (elem instanceof List<?>) {
                result.addAll(flatten((List<Object>) elem));
            }
        }
        return result;
    }

    // ------------ 功能 4：計算最大深度 ------------

    public static int maxDepth(List<Object> nestedList) {
        int max = 1;
        for (Object elem : nestedList) {
            if (elem instanceof List<?>) {
                max = Math.max(max, 1 + maxDepth((List<Object>) elem));
            }
        }
        return max;
    }
}
