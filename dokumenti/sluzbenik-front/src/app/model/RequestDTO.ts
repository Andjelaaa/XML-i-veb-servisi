import { IzborDTO } from './IzborDTO';

export interface RequestDTO{
    text?: string;
    izbori?: Array<IzborDTO>;
}
