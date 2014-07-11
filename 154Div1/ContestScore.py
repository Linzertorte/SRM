class ContestScore:

    def sortResults(self, data):
        def my_int(s):
            a,b=map(int,s.split('.'))
            return a*10+b
        def feature(entry, score_list):
            e = entry.split(' ')
            name,scores = e[0],e[1:]
            scores = map(my_int,scores)
            rank = 0
            total = 0
            for i in xrange(len(scores)):
                rank += score_list[i].index(scores[i])+1
                total += scores[i]
            return (rank,-total,name)

        if len(data)==0:
            return ()
        n_judges = len(data[0].split(' ')) - 1
        score_list = [[] for i in xrange(n_judges)]
        for entry in data:
            scores = entry.split(' ')[1:]
            scores = map(my_int, scores)
            for i in xrange(n_judges):
                score_list[i].append(scores[i])
        score_list = map(lambda x: sorted(x,key=lambda y:-y),score_list)
        data = map(lambda x:feature(x,score_list),data)
        data = sorted(data)
        result = ()
        for d in data:
            result+=('%s %d %.1f'%(d[2],d[0],-d[1]/10.0),)
        return result
