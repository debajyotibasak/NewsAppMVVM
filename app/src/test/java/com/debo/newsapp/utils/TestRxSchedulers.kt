package com.debo.newsapp.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestRxSchedulers : RxSchedulers {
    override fun runOnBackground(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun compute(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun androidThread(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun internet(): Scheduler {
        return Schedulers.trampoline()
    }
}