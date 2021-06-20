function n=count_func(x, y)
    [MX, MY] = ndgrid(x, y);
    n = (sum((MX <= MY), 1))';
end