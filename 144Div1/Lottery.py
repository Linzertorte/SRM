class Lottery:
    def sortByOdds(self, rules):
        def fac(n):
            if n<=0:
                return 1
            return fac(n-1)*n
        def count_p(rule):
            name, d = rule.split(': ')
            c,b,s,u = d.split(' ')
            c,b = map(int,(c,b))
            if(s=='F'):
                if(u=='F'):
                    return (c**b,name)
                else:
                    return (fac(c)/fac(c-b),name)
            else:
                if(u=='F'):
                    c+=(b-1)
                return (fac(c)/(fac(c-b)*fac(b)),name)
        def get_name(rule):
            name,_ = rule.split(': ')
            return name
        #print map(count_p,rules)
        return map(get_name,sorted(rules,key=count_p))
