% detecting faces in an image
% assignment for computer vision (Ajou Univ.)

clear
close all

img = im2double(imread('TestImage.jpg'));

face = im2double(imread('FaceImage.jpg'));
[sy, sx]= size(face);

%parameters
stepsize = 2;
threshold = 18;
nmsval = 0.05;

ret = detFace(face, img, stepsize);
fprintf('The max output value is = %f\n',max(max(ret)));

%[y,x]=find(ret>threshold);
[y,x]=find(0<ret & ret<threshold);
value = ret(sub2ind(size(ret), y, x));
boxes(:,1)=x*stepsize;
boxes(:,2)=y*stepsize;
boxes(:,3)=x*stepsize+sx;
boxes(:,4)=y*stepsize+sy;
boxes(:,5)=value;

% Non-maximum suppression
pick=nms(boxes, nmsval);

figure, imshow(img);
hold on;

for i=1:length(pick)
    rectangle('position', [boxes(pick(i), 1), boxes(pick(i), 2),sx,sy], 'Edgecolor', 'r');
end

% main function
function [ret]=detFace(H, F, stepsize)
    [height_kernel, width_kernel]=size(H);
    [height_image, width_image]=size(F);
  %  ret=[];
    ret=zeros(height_image/stepsize, width_image/stepsize);
    
    u=0;  
    for y=1:stepsize:(height_image-height_kernel)
        u=u+1;
        v=0;
        for x=1:stepsize:(width_image-width_kernel)
            v=v+1;         
            %ret(u,v) = CrossCorrelation(H, F, y, x);
            %ret(u,v) = ManhattanDistance(H, F, y, x);
            ret(u,v) = EuclideanDistance(H, F, y, x);
        end
    end
    
    return
end