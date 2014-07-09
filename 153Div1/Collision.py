class Collision:
    def probability(self, assignments, ids):
        p1 = 1.0

        m1 = ids
        m2 = ids
        if sum(assignments)>ids:
            return 0.0
        for assignment in assignments:
            m2 = ids
            for i in xrange(assignment):
                p1*=(1.0*m1/m2)
                m1-=1
                m2-=1
        p2 = 1.0
        m = ids
        for i in xrange(sum(assignments)):
            p2 *= (1.0*m/ids)
            m -= 1
        return p1-p2
