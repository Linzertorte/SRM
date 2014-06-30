public class Iditarod {
    private int get_minutes(String[] tokens){
        int h = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        String p = tokens[2];
        int d = Integer.parseInt(tokens[4]);
        int total = d*24*60+h*60+m;
        if(p.equals("PM") && h!=12) total += 12*60;
        if(p.equals("AM") && h==12) total -= 12*60;
        return total - 32*60;
    }
    public int avgMinutes(String[] times){
        int total = 0;
        for(int i=0;i<times.length;i++){
            String[] tokens = times[i].split("[: ]|, ");
            total += get_minutes(tokens);
        }
        return (int)Math.floor(total*1.0/times.length+0.5);
    }
}
