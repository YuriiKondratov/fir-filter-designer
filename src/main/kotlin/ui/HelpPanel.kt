package ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.m2.Markdown

@Composable
fun HelpPanel() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val stateVertical = rememberScrollState(0)

        Box(
            modifier = Modifier
                .verticalScroll(stateVertical)
                .padding(10.dp)
        ) {
            Markdown("""
                #### Инструкция по использованию приложения
                  
                    
                ##### Окно "Проектирование"
                Для того, чтобы спроектировать фильтр:
                1. задайте его характеристики (ограничения на значения полей можно увидеть, наведя курсор на иконку информации рядом с полем);
                2. нажмите кнопку "**Рассчитать**".
                
                После этого на правой панели отобразятся характеристики фильтра.
                
                Для сохранения коэффициентов спроектированного фильтра в текстовый файл нажмите кнопку "**Экспортировать**".
                
                Для использования фильтра в окнах "**Сравнение**" и "**Применение**" его необходимо сохранить при помощи кнопки "**Запомнить**". 
                
                В открывшемся диалоговом окне нужно задать уникальное имя фильтра. 
                
                _Обратите внимание_, что, если фильтр с заданным именем уже существует, его характеристики будут перезаписаны.
                
                
                ##### Окно "Сравнение"
                В данном окне отображается список фильтров, которые были сохранены при помощи кнопки "**Запомнить**" в окне "Проектирование". 
                
                Для того, чтобы удалить фильтр из списка, нажмите на иконку удаления справа от его имени. 
                
                _Обратите внимание_, что удаление фильтра из списка также не позволит использовать его в окне "Применение".
                
                Для отображения фильтра на правой панели с визуализацией необходимо выбрать его путем нажатия на чекбокс, расположенный справа от иконки удаления.
           
           
                ##### Окно "Применение"
                Для демонстрации результатов фильтрации:
                1. выберите файл при помощи кнопки "**Выбрать .wav файл**";
                2. задайте интересующий Вас момент времени при помощи ползунка;
                4. нажмите кнопку "**Выбрать фильтр**";
                5. в открывшемся окне выберите один из фильтров, сохраненных в окне "Проектирование" при помощи кнопки "**Запомнить**";
    
                После этого на правой панели отобразятся два графика - АЧХ сигнала до и после фильтрации.
                
                Чтобы оценить результат фильтрации в другой момент времени, задайте его при помощи ползунка и нажмите кнопку "**Показать АЧХ**".
            """.trimIndent(),
                modifier = Modifier.padding(10.dp)
            )
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd)
                .fillMaxHeight(),
            adapter = rememberScrollbarAdapter(stateVertical)
        )
    }
}
