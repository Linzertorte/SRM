import itertools
class MNS:
    def combos(self, numbers):
        def is_magic(com):
            return sum(com[0:3])\
            ==sum(com[3:6])\
            ==sum(com[6:9])\
            ==(com[0]+com[3]+com[6])\
            ==(com[1]+com[4]+com[7])\
            ==(com[2]+com[5]+com[8])
            
        vis = set()
        cnt = 0
        for com in itertools.permutations(numbers):
            if com in vis:
                continue
            #vis.add(com)
            if is_magic(com):
            	vis.add(com)
                cnt +=1
        return cnt
