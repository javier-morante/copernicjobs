
export default function moduleIconParser(offerName) {

    switch (offerName) {
        case("myOffers"):
            return {
                icon: "file-alt",
                name: "Les meves ofertes",
                link: "/my-offers"
            }

        case("createOffer"):
            return {
                icon: "file-medical",
                name: "Crear oferta",
                link: "/create-offer"
            }

        case("incidents"):
            return {
                icon: "exclamation-triangle",
                name: "Incidències",
                link: "/incidences"
            }


        case("laboralInformation"):
            return {
                icon: "info-circle",
                name: "Informació laboral",
                link: "/laboral-information"
            }

        case("offers"):
            return {
                icon: "file-alt",
                name: "Ofertes",
                link: "/offers"
            }

        case("myInscriptions"):
            return {
                icon: "file-signature",
                name: "Les meves inscripcions",
                link: "/my-inscriptions"
            }

        case("requests"):
            return {
                icon: "paper-plane",
                name: "Sol·licituds",
                link: "/requests-management"
            }

        case("roles"):
            return {
                icon: "user-cog",
                name: "Rols",
                link: "/role-management"
            }

        case("users"):
            return {
                icon: "users",
                name: "Usuaris",
                link: "/user-management/administrator"
            }

        case("reports"):
            return {
                icon: "chart-pie",
                name: "Informes",
                link: "/application-reports"
            }
    }
}