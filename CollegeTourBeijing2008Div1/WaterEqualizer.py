class WaterEqualizer:
    def minTime(self, waterVolumes):
        total = sum(waterVolumes)
        n = len(waterVolumes)
        avg = 1.0 * total / n
        result = 0
        for i in waterVolumes:
            if i < avg:
                result += avg - i
        return result
