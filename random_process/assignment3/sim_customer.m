function [M]=sim_customer(lambda, mu, var, t)
    s = poissonarrivals(lambda, max(t));
    y = s + normalrv(mu, var, length(s));
    A = count_func(s, t);
    D = count_func(y, t);
    M = A - D;
    figure(1);
    plot(t, M);
    figure(2);
    plot(t, A, '-b', t, D, ':r');
end