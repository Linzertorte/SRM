class DividingIntoTeams:
    def findYourTeam(self, alexFriends, bobFriends, charlieFriends, kk):
        n = len(alexFriends)
        picked = [0]*(n+1)
        i,j,k = 0,0,0
        while True:
            while i<n and picked[alexFriends[i]]:
                i+=1
            if i<n:
                if kk==alexFriends[i]:
                    return "Alex"
                picked[alexFriends[i]] = 1
                i+=1
            
            while j<n and picked[bobFriends[j]]:
                j+=1
            if j<n:
                if kk==bobFriends[j]:
                    return "Bob"
                picked[bobFriends[j]] = 1
                j+=1
            
            while k<n and picked[charlieFriends[k]]:
                k+=1
            if k<n:
                if kk==charlieFriends[k]:
                    return "Charlie"
                picked[charlieFriends[k]] = 1
                k+=1
            
