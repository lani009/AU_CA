function showImage(data)

figure
image = [];
n=0;

[~,m]=size(data);
flag = 0;
for y=1:10
    row=[];
    for x=1:10
        n=n+1;
        if (n>m) 
            flag = 1;
            break;
        end
        i = data(:,n);
        i = i-min(i);
        i = i/max(i);
        y = reshape(i, [32 32 1]);
        %y = imrotate(y, -90);
        row = [row y];
    end
    image=[image; row];
    if (flag==1)
        break;
    end
end

imshow(image);

return