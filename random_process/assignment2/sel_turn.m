function []=sel_turn()
    load('studentPMF.mat', 'studentPMF');
    [sizeX, sizeY] = size(studentPMF);
    trashold = 5;
    cleaningProb = 0; % 청소를 안할 확률

    for X = 1 : sizeX
        for Y = 1 : sizeY
            if (X + Y < trashold)
                cleaningProb = cleaningProb + studentPMF(X, Y);
            end
        end
    end

    for studentI = 1 : 20
        if (rand(1) < cleaningProb)
            fprintf("%d\n", studentI);
        end
    end
end