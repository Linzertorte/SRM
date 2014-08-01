class Dating:
    def dates(self, circle, k):
        circle=list(circle)

        matches = ""
        chooser = 0
        while True:
            n = len(circle)
            if n==0:
                break
            chooser = (chooser+k-1)%n
            hottest = 10000
            j = -1
            if circle[chooser]>='a' and circle[chooser]<='z':
                for i in xrange(n):
                    if circle[i]>='A' and circle[i]<='Z' and ord(circle[i])<hottest:
                        hottest = ord(circle[i])
                        j = i
            else:
                for i in xrange(n):
                    if circle[i]>='a' and circle[i]<='z' and ord(circle[i])<hottest:
                        hottest = ord(circle[i])
                        j = i
            if j==-1:
                break
            matches+=" "+circle[chooser]+circle[j]
            #print matches
            if j < chooser:
                chooser -= 1
            del circle[j]
            del circle[chooser]


        if len(matches) ==0:
            return matches
        else:
            return matches[1:]
