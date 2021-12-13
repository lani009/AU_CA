msg_length = 8;

for i = 0 : 5
    msg = rand(1, msg_length) > 0.5;
    poly = [1 0 0 0 0 1 1];

    [crc, frame] = crc_gen(msg, poly);
    
    received_frame = frame;

    for j = 0 : msg_length
        if rand() < 0.05
            received_frame(j) = ~received_frame(j);
        end
    end

    if crc_check(received_frame, poly) == 0
        result = "Accept";
    else
        result = "Reject";
    end
    msg = double(msg);

    fprintf("msg: %s\ncrc: %s\noriginal frame:"+ ...
        "%s\nreceived frame: %s\nresult: %s\n\n", ...
        mat2str(msg), mat2str(crc), mat2str(frame), mat2str(received_frame), result);
end