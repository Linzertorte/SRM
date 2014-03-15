
from collections import deque
from copy import deepcopy
class EmoticonsDiv2:
    def printSmiles(self,smiles):
        V,init = set(), {'chat':1,'clip':0,'step':0}
        V.add((1,0))
        Q = deque()
        Q.append(init)
        while len(Q) !=0:
            head = Q.popleft()
            if head['chat'] == smiles:
                return head['step']
            anew = deepcopy(head)
            anew['clip']=anew['chat']
            anew['step'] += 1
            if (anew['chat'],anew['clip']) not in V:
                V.add((anew['chat'],anew['clip']))
                Q.append(anew)
            anew = deepcopy(head)
            anew['chat']+=anew['clip']
            anew['step']+=1
            if(anew['chat']>smiles):
                continue
            if (anew['chat'],anew['clip']) not in V:
                V.add((anew['chat'],anew['clip']))
                Q.append(anew)
        return -1

