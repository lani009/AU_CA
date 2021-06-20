function s = poissonarrivals(lambda, T)
    n = ceil(lambda * T);
    s = cumsum(exponentialrv(lambda, n));
    while (s(length(s)) < T)
        s_new = s(length(s))+ ...
            cumsum(exponentialrv(lambda, n));
        s=[s;s_new];
    end
    s = s(s <= T);
end