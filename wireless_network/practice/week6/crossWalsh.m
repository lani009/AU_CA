walsh = walshcode(5);

w1 = walsh(1, :);
w2 = walsh(2, :);


stem(crosscorr(w1, w2));