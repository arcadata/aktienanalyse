public class TA
{
  static double rsi(int anzahl, int laenge, double kurs[])
  {
    int i, gewinn, verlust;

    gewinn = 0;
    verlust = 0;
    for (i=anzahl-laenge; i<anzahl; i++)
    {
      if (kurs[i]>kurs[i-1]) gewinn++;
      if (kurs[i]<kurs[i-1]) verlust++;
    }
    double result = 100.0 - (100.0 / (1.0 + ((double)gewinn / (double)verlust)));
    return result;
  }

  static double gd(int anzahl, int laenge, double kurs[])
  {
    int i;
    double result = 0.0;

    for (i=anzahl-laenge; i<anzahl; i++) result += kurs[i];
    result /= laenge;
    return result;
  }

  static double rs(int anzahl, int laenge, double kurs[])
  {
    int i;
    double result = 0.0;

    for (i=anzahl-laenge; i<anzahl-1; i++) result += kurs[i];
    result /= laenge-1;
    result = kurs[anzahl-1]/result;
    return result;
  }
}

