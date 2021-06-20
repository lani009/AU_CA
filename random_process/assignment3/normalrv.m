function r=normalrv(mu, var, m)
    r = mu + sqrt(var) * randn(m, 1);
end