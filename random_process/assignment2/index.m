p = 0.5;
myGeo = geometricrv(p, 5000);
pts = (0: 1 : 10);
ksdensity(myGeo, pts);
title("myGeo PDF");

matlabGeo = geornd(p, 1, 5000);
pts = (0: 1 : 10);
ksdensity(matlabGeo, pts);
title("matlabGeo PDF");