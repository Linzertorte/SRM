class IPConverter:
    def possibleAddresses(self, ambiguousIP):

        def dfs(i,ip,left,ips):
            if i==4:
                if len(left)==0:
                    ips.append(ip[1:])
                return
            if len(left)==0:
                return
            for j in xrange(1,4):
                if int(left[:j])<256:
                    dfs(i+1,ip+'.'+left[:j],left[j:],ips)
                if j==1 and left[0]=='0':
                    break


        ips = []
        dfs(0,'',ambiguousIP,ips)
        return sorted(tuple(set(ips)))
