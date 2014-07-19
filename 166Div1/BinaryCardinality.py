
class BinaryCardinality:
    def arrange(self, numbers):
        def cardinal(n):
            cnt = 0
            while n!=0
                if n&1 == 1:
                    cnt+=1
                n >>= 1
            return cnt
        return sorted(numbers,key=lambda n:(cardinal(n),n))
