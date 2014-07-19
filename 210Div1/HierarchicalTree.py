import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

class Node{
    Set<Node> children = new HashSet<Node>();
    public boolean visited = false;
    public boolean hasError = false;
    public int descents = 0;
    String id;
    public Node(String id){
        this.id = id;
    }
    public void addChild(Node c){
        children.add(c);
    }
    public void dfs(){
        if(visited) {
            hasError = true;
            return;
        }
        visited = true;
        for(Node kid:children) {
            kid.dfs();
            descents += 1+kid.descents;
        }
    }
    public String toString(){
        return id+": "+descents;
    }
}
public class HierarchicalTree
{
    public String[] countDescendants(String[] parentData)
    {
        if(parentData.length==0) return new String[]{"ROOT: 0"};
        if(parentData.length == 1 && parentData[0].length()==0) return new String[]{"ROOT: 0"};
        HashMap<String,Node> nodes = new HashMap<String,Node>();
        nodes.put("ROOT",new Node("ROOT"));
        String parent = "";
        for(String s:parentData)
            parent += s;
        String[] pairs = parent.split(" ");

        for(String pair:pairs){
            String[] two = pair.split(",");
            if(!nodes.containsKey(two[0])) nodes.put(two[0],new Node(two[0]));
            if(!nodes.containsKey(two[1])) nodes.put(two[1],new Node(two[1]));
            nodes.get(two[1]).addChild(nodes.get(two[0]));
        }

        nodes.get("ROOT").dfs();
        String result[] = new String[nodes.size()];
        int i=0;
        for(Node n:nodes.values()) {
            if (n.hasError || !n.visited) return new String[0];
            result[i++] = n.toString();
        }

        Arrays.sort(result);
        return result;

    }
}
