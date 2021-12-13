function [next_time] = saloha(now_time)

    global STANDBY TRANSMIT COLLISION
    global Srate Plen
    global Mnum Mplen Mstate
    global Tint Rint
    global Spnum Splen Tplen Wtime

    persistent mgtime mtime slot_len

    if now_time < 0
        rand('state', sum(100 * clock));
        slot_len = Plen / Srate;
        mgtime = -Tint * log(1 - rand(1, Mnum));
        next_time = ceil(mgtime / slot_len);
        mtime = next_time * slot_len;
        Mstate = zeros(1, Mnum);
        Mplen(1:Mnum) = Plen;
        next_time = min(mtime);
        return
    end

    idx = find(mtime == now_time & Mstate == TRANSMIT);

    if ~isempty(idx)
        Spnum = Spnum + 1;
        Splen = Splen + Mplen(idx);
        Wtime = Wtime + now_time - mgtime(idx);
        Mstate(idx) = STANDBY;
        mgtime(idx) = now_time - Tint * log(1 - rand);
        next_time = ceil(mgtime(idx) / slot_len);
        mtime(idx) = next_time * slot_len;
    end

    idx = find(mtime == now_time & Mstate == COLLISION);

    if ~isempty(idx)
        Mstate(idx) = STANDBY;
        mtime(idx) = now_time - Rint * log(1 - rand(1, length(idx)));
        next_time = ceil(mtime(idx) / slot_len);
        mtime(idx) = next_time * slot_len;
    end

    idx = find(mtime == now_time);

    if ~isempty(idx)
        Mstate(idx) = TRANSMIT;
        mtime(idx) = now_time + Mplen(idx) / Srate;
        next_time = ceil(mtime(idx) / slot_len);
        mtime(idx) = next_time * slot_len;
        Tplen = Tplen + sum(Mplen(idx));
    end

    next_time = min(mtime);
