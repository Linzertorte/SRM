class MedalTable:
    def generate(self, results):
        countries = dict()
        for result in results:
            i = 0
            for c in result.split(' '):
                if c not in countries:
                    countries[c] = (0,0,0)
                g,s,b = countries[c]
                if i == 0:
                    countries[c]=(g+1,s,b)
                if i==1:
                    countries[c]=(g,s+1,b)
                if i==2:
                    countries[c]=(g,s,b+1)
                i+=1
        result = []
        for c in countries:
            result.append((c,)+countries[c])
        result = sorted(result,key=lambda x:(-x[1],-x[2],-x[3],x[0]))
        return tuple(map(lambda x: ' '.join(map(str,x)), result))
