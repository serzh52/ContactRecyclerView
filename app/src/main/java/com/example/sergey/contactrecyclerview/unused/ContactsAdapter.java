package com.example.sergey.contactrecyclerview.unused;

/*

*/
/**
 * Created by Sergey on 28.06.2016.
 *//*

public class ContactsAdapter extends ArrayAdapter<Contact>{//
    Context context;
    int resource;
    List<Contact> contact;
//переменные экземпляра

    public ContactsAdapter(Context context,int resource ) {//создаем конструктор с параметрами
        super(context,resource );
        //переменные родительского класса
        this.resource = resource;
        this.context = context;
//переменные экземпляра(текущего класса)
    }
    static class ViewHolder {
        //создаем статическийс внутренний  класс вью холдер который может использоваться без создания объекта
        //с 2 переменными текст вью нейм и намбер
        //он будет постоянно хранить ссылку на нужные элементы
        public TextView name;
        public TextView number;
    }
    //переопределяем метод гетвью с параметрами
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;//инициируем переменную холдер
        if (convertView == null) {//сравниваем,если конверт вью нул то
            LayoutInflater inflater = LayoutInflater.from(context);//создаем объект инфлейтер клсасса лояут инфлейтер который "надуваем" из контекст
            convertView = inflater.inflate(resource, parent, false);//заполняем конверт вью данными из параметров
            holder = new ViewHolder();//иницилизируем переменную холдер

            holder.name = (TextView) convertView.findViewById(R.id.contact_name);//сохраняем ссылку на текст вью в holder.name
            holder.number = (TextView) convertView.findViewById(R.id.contact_phone);//сохраняем ссылку на текст вью в holder.number
            convertView.setTag(holder);//сохранияем данные для холдера
        }else {//иначе
            holder = (ViewHolder) convertView.getTag();//получаем данные для
        }
        Contact contact = getItem(position);//получаем позицию элемента в списке

        holder.name.setText(contact.getName());//сохраняем полученные данные  для поля нейм
        holder.number.setText(contact.getNumber());//сохраняем полученные данные для поля намбер

        return convertView;//возвращаем данные конвервью
        }
}

*/
