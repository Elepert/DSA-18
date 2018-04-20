public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long failingVersion = -1;
        long start = 0;
        long end = n;
        long mid = n/2;
        while (failingVersion == -1){
            boolean currentStatus = isBadVersion.isFailingVersion(mid);
            if (end-start == 1){
                failingVersion = mid+1;
            } else {
                if (currentStatus == true) {
                    end = mid;
                    mid = start+ (end - start) / 2;
                } else if (currentStatus == false) {
                    start = mid;
                    mid = start + (end - start) / 2;
                }
            }
        }

        return failingVersion;
    }
}
