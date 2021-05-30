function []=sel_turn()
    load('studentPMF.mat', 'studentPMF');
    [sizeX, sizeY] = size(studentPMF);
    studentVec = zeros(sizeX * sizeY, 1);
    overFiveVec = zeros(sizeX * sizeY, 1);

    for i = 1 : sizeX
        for j = 1 : sizeY
            k = (i-1)*sizeX + j;
            overFiveVec(k) = i + j;
            if (~(k == 1))
                studentVec(k) = studentPMF(i, j) + studentVec(k-1);
            else
                studentVec(k) = studentPMF(i, j);
            end
        end
    end

    studentRandom = rand(1, 20);

    for i = 1 : size(studentRandom')
        for j = 1 : sizeX * sizeY
            if (studentRandom(i) <= studentVec(j))
                if (overFiveVec(j) < 5)
                    fprintf("%d\n", i);
                end
                break;
            end
        end
    end
end