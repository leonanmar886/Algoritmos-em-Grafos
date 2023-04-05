import java.util.*;
class Main {
    public static List<String> splitStringByLines(String input) {
        List<String> lines = new ArrayList<>();

        String[] inputSplited = input.split("\n");

        Collections.addAll(lines, inputSplited);

        return lines;
    }

    public static List<List<String>> splitStringBySpaces(List<String> input) {
        List<List<String>> result = new ArrayList<>();

        for (String line : input){
            String[] inputSplited = line.split(" ");
            List<String> pair = new ArrayList<>();
            Collections.addAll(pair, inputSplited);
            result.add(pair);
        }

        return result;
    }

    public static String receiveInput(){
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            builder.append(linha).append("\n");

            if (linha.trim().isEmpty()) {
                return builder.toString().replaceAll("\\n\\n$", "");
            }
        }

        return builder.toString();
    }

    public static String buildRelatedComponents(List<List<String>> edges, String n){
        Queue<String> vertices = new LinkedList<>();
        Queue<String> neighborhood = new LinkedList<>();
        List<String> relatedComponents = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i <= Integer.parseInt(n);  i++) {
            vertices.add(Integer.toString(i));
        }

        while(!vertices.isEmpty()){
            String currentVert = vertices.remove();
            neighborhood.add(currentVert);
            relatedComponents.add(currentVert);
            while(!neighborhood.isEmpty()){
                String aux = neighborhood.remove();
                vertices.remove(aux);
                Iterator<List<String>> iterator = edges.iterator();
                while(iterator.hasNext()) {
                    List<String> edge = iterator.next();
                    if (edge.contains(aux)) {
                        int position = edge.indexOf(aux) == 0 ? 1 : 0;
                        neighborhood.add(edge.get(position));
                        if(!relatedComponents.contains(edge.get(position))){
                            relatedComponents.add(edge.get(position));
                        }
                        iterator.remove();
                    }
                }
            }
            relatedComponents.stream()
                    .sorted((s1, s2) -> Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2)))
                    .forEach(string -> builder.append(string).append(" "));
            builder.replace(builder.lastIndexOf(" "), builder.length(), "\n");
            relatedComponents.clear();
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public static void main(String[] args) {
        String input = receiveInput();
        List<String> edgesBeforeSplitBySpaces = splitStringByLines(input);
        edgesBeforeSplitBySpaces.remove(0);
        edgesBeforeSplitBySpaces.remove(0);
        edgesBeforeSplitBySpaces.remove(1);

        String n = edgesBeforeSplitBySpaces.remove(0).split("n=")[1];
        List<List<String>> edges = splitStringBySpaces(edgesBeforeSplitBySpaces);

        edgesBeforeSplitBySpaces.clear();

        System.out.println(buildRelatedComponents(edges, n));

    }
}