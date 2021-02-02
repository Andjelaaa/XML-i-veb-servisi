import { AdresaDTO } from './AdresaDTO';
import { IzborDTO } from './IzborDTO';
import { PodnosilacDTO } from './PodnosilacDTO';

export interface RequestDTO{
    text?: string;
    uri?: string;
    podnosilac?: PodnosilacDTO,
    adresa?: AdresaDTO,
    drugiPodaciZaKontakt?: string,
    nazivOrganaVlasti?: string,
    sedisteOrgana?: string,
    naslov?: string,
    trazeneInformacije?: string,
    datum?: string,
    mesto?: string,
    izbori?: Array<IzborDTO>;
}
