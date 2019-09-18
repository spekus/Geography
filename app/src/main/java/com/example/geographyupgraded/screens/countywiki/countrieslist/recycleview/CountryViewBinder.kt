import android.widget.TextView
import androidx.navigation.NavController
import com.example.geographyupgraded.R
import com.example.geographyupgraded.network.models.Country
import com.example.geographyupgraded.screens.countywiki.countrieslist.CountriesListFragmentDirections
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import com.google.android.material.card.MaterialCardView

class CountryViewBinder(
    private val navController: NavController
) : ViewBinder.Binder<Country> {
    override fun bindView(model: Country, finder: ViewFinder, payloads: MutableList<Any>) {
        val capital = finder.find(R.id.countries_list_country_capital) as TextView
        val countryName = finder.find(R.id.countries_list_country_name) as TextView
        capital.text = model.capital
        countryName.text = model.name

        val countryCard = finder.find(R.id.countries_list_country_card) as MaterialCardView
        countryCard.setOnClickListener {
            navController.navigate(
                CountriesListFragmentDirections.ActionCountriesListToCountryProfileFragment(
                    model.name
                )
            )
        }
    }
}