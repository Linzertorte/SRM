class ErdosNumber:
    #tuple (string)
    def calculateNumbers(self, publications):
        authorNode={}
        cnt = 0
        graph=[[1000 for i in range(50)] for j in range(50)]
        for publication in publications:
            for author_i in publication.split():
                for author_j in publication.split():
                    if author_i not in authorNode:
                        authorNode[author_i] = cnt
                        cnt += 1
                    if author_j not in authorNode:
                        authorNode[author_j] = cnt
                        cnt += 1
                    i=authorNode[author_i]
                    j=authorNode[author_j]
                    if i==j:
                        graph[i][j]=graph[j][i]=0
                    else:
                        graph[i][j]=graph[j][i]=1

        
        for k in range(cnt):
            for i in range(cnt):
                for j in range(cnt):
                    if graph[i][k]+graph[k][j]<graph[i][j]:
                        graph[i][j]=graph[i][k]+graph[k][j]
        s=()
        for key in authorNode.keys():
            i = authorNode[key]
            j = authorNode['ERDOS']
            if graph[i][j]==1000:
                s+=(key,)
            else:
                s+=(key+" "+str(graph[i][j]),)
        return sorted(s)
