
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

class Path implements Comparable {

    public String[] paths;

    public Path(String[] paths) {
        this.paths = paths;
    }

    @Override
    public int compareTo(Object o) {
        Path p = (Path) o;
        for (int i = 0; i < this.paths.length; i++) {
            if (!this.paths[i].equals(p.paths[i])) {
                return this.paths[i].compareTo(p.paths[i]);
            }
        }
        return 0;
    }

    public int match(Path p) {
        for (int i = 0; i < Math.min(p.paths.length, paths.length); i++) {
            if (!p.paths[i].equalsIgnoreCase(paths[i])) {
                return i;
            }
        }
        return -1;
    }
}

public class DirectoryTree {

    public void go(int cur, int dep, Path[] paths, ArrayList<String> pretty) {
        if (cur == paths.length) {
            return;
        }
        if (dep == paths[cur].paths.length) {
            go(cur + 1, 0, paths, pretty);
            return;
        }
        int d = 0;
        if (dep == 0) {
            if (cur != 0) {
                dep = paths[cur].match(paths[cur - 1]);
            }

        }
        String line = "";
        for(int i=0;i<dep-1;i++)
            line+="  ";
        if(dep!=0)line+="+-";
        line+=paths[cur].paths[dep];
        pretty.add(line);
        go(cur, dep + 1, paths, pretty);

    }
    public String[] postProcess(String [] lines){
        char[][] mat = new char[lines.length][];
        int n = lines.length;
        int len = Integer.MIN_VALUE;
        for(int i=0;i<lines.length;i++) {
            mat[i]=lines[i].toCharArray();
            len = Math.max(len, lines[i].length());
        }
        String[] result = new String[lines.length];
        for(int j=0;j<len;j+=2){
            ArrayList<Integer> plus = new ArrayList<Integer>();
            for(int i=0;i<n;i++){
                if(j>=mat[i].length) continue;
                if(mat[i][j]=='+') plus.add(i);
            }
            for(int k=0;k<plus.size()-1;k++){
                int i1=plus.get(k);
                int i2=plus.get(k+1);
                boolean flag = true;
                for(int i=i1+1;i<i2;i++){
                    if(j>=mat[i].length || mat[i][j]!=' ') {
                        flag = false;
                        break;
                    }
                }
                if(flag)
                    for(int i=i1+1;i<i2;i++)
                        mat[i][j]='|';
            }
        }
        
        for(int i=0;i<lines.length;i++)
            result[i] = new String(mat[i]);
        return result;
    }
    public String[] display(String[] files) {
        Path[] paths = new Path[files.length];
        for (int i = 0; i < files.length; i++) {
            paths[i] = new Path(("ROOT" + files[i]).split("/"));
        }
        ArrayList<String> pretty = new ArrayList<>();
        Arrays.sort(paths);
        go(0, 0, paths, pretty);
        String[] result = (String[]) pretty.toArray(new String[0]);
        return postProcess(result);
    }
}
