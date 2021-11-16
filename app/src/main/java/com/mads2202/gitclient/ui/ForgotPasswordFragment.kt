package com.mads2202.gitclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mads2202.gitclient.R
/*Отличия rxJava2 от rxJava3
* Базовая версия Java теперь увеличена до 8*;
Появилась поддержка таких фич языка, как:
— Streams
— Stream Collectors
— Optional
— CompletableFeature

В свою очередь, разработчики убрали поддержку таких фич, как:
java.time.Duration – порождает большое количество перегрузок, всегда может быть заменено на time + unit;
java.util.function – не может кидать исключения, при этом перегрузки способны создать лишнюю “двусмысленность”.
*
* Ранее одна из проблем RxJava 2 заключалась в том,
*  что в некоторых кейсах ошибки могли потеряться и не быть обработаны.
*  Теперь в RxJava 3 отписка от источника, который может выбросить ошибку,
*  инициирует прокидывание этой ошибки в общий обработчик ошибок через RxJavaPlugins.onError()
*
* В RxJava 2 существовала проблема с горячими источниками:
*  при получении ConnectableObservable терминального события новые подключения игнорировали все элементы
*  и получали только событие terminate.

В RxJava 3 появилась функция сброса состояния ConnectableObservable с помощью функции reset(),
*  чтобы дать возможность вновь подключенным подписчикам обрабатывать данные

* Новые типы: Supplier
На самом деле мне трудно судить, что важного и принципиального изменилось. Статья на хабре источник
https://habr.com/ru/company/simbirsoft/blog/510400//
 */
class ForgotPasswordFragment : Fragment() {
    companion object {
        fun newInstance(): ForgotPasswordFragment {
            val args = Bundle()
            val fragment = ForgotPasswordFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.retrieve_password_screen_layout, container, false)
        return view
    }
}